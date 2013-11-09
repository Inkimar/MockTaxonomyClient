package se.nrm.mediaserver.restful;

/**
 *
 * @author ingimar
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import se.nrm.mediaserver.media3.domain.Bubble;
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.media3.domain.Media;
import se.nrm.mediaserver.service.MediaService;
import se.nrm.mediaserver.util.FilePropertiesHelper;
import se.nrm.mediaserver.util.JNDIFetchRemote;
import se.nrm.mediaserver.util.MimeParser;


/**
 * Att hämta en bild, hårdkodad
 * http://localhost:8080/MediaServerResteasy/media/bild/png
 * @author ingimar
 */
@Path("/media")
// @Stateless, måste paketeras rätt om du ska använda en böna ... 
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public class MediaResourceService implements MediaResource {

    @Context
    private UriInfo uriInfo;

    final MediaService bean = JNDIFetchRemote.outreach();

    @POST
    @Path("/upload-file")
    @Consumes("multipart/form-data")
    @Produces("text/plain")
    @Override
    public Response createNewFile(@MultipartForm FileUploadForm form) {

        String uuIdFilename = getUUID();

        String uploadedFileLocation = absolutePathToFile(uuIdFilename);
        writeToFile(form, uploadedFileLocation);

        File fileHandle = new File(uploadedFileLocation);
        String mimeType = "unkown";
        try {
            mimeType = MimeParser.getMimeFromFileContentAndExtension(fileHandle, uuIdFilename);

        } catch (IOException ioEx) {
            Logger.getLogger(MediaResourceService.class.getName()).log(Level.SEVERE, null, ioEx);
        }

        // beroende vilken Media det är ...
        Media media = new Image();
        media.setUuid(uuIdFilename);
        media.setFilename(form.getFileName());
        media.setMimetype(mimeType);
        media.setOwner(form.getOwner());
        media.setVisibility(form.getAccess());

        writeToDatabase(media);

        String responseOutput = "File uploaded/saved to : " + uploadedFileLocation;

        return Response.status(200).entity(responseOutput).build();
    }

    @POST
    @Path("/bubble")
    public Response createN(JAXBElement<Bubble> bubbleJaxb) {
        System.out.println("Bubble on");
        return Response.status(200).entity("hej").build();
    }

    @POST
    @Path("/testagain")
    public Response createNe(JAXBElement<Image> imageJaxb) {
        System.out.println("createNewImage");
        // klassen ska kontrollera UUID, den ska fylla i det
        Media image = imageJaxb.getValue();
        writeToDatabase(image);
        URI bookUri = uriInfo.getAbsolutePathBuilder().path(image.getUuid().toString()).build();
        return Response.created(bookUri).build();
    }

    @POST
    @Path("/test")
    public Response createNewImage(JAXBElement<Image> imageJaxb) {
        System.out.println("createNewImage");
        // klassen ska kontrollera UUID, den ska fylla i det
        Media image = imageJaxb.getValue();
        writeToDatabase(image);
        URI bookUri = uriInfo.getAbsolutePathBuilder().path(image.getUuid().toString()).build();
        return Response.created(bookUri).build();
    }

    /**
     * @param uuid
     * @return
     */
    @GET
    @Path("/image/{uuid}")
    @Override
    public Image getMediaAsXML(@PathParam("uuid") String uuid) {
        Image media = (Image) bean.get(uuid);
        System.out.println("Image is " + media);
        return media;
    }

    /**
     * @param uuid
     */
    @DELETE
    @Path("/image/{uuid}")
    @Override
    public void deleteMediaMetaData(@PathParam("uuid") String uuid) {
        System.out.println("delete record with uuid as " + uuid);
        bean.delete(uuid);
    }

    @GET
    @Path("/all")
    @Produces("text/plain")
    public List getAllMediaMetaData() {
        System.out.println("getting all");
        List<Image> images = bean.getAll();

        return images;
    }

    /**
     * @return
     */
    @GET
    @Produces("text/plain")
    public String getTesting() {
        return "Testar ... igen klockan 16:22";
    }
    // bryt ut. egen klass

    private String absolutePathToFile(String uuid) {
        String IMAGE_PATH = FilePropertiesHelper.getImagesFilePath();

        StringBuilder tmpPath = new StringBuilder(IMAGE_PATH);
        tmpPath.append(uuid.charAt(0)).append("/").append(uuid.charAt(1)).append("/").append(uuid.charAt(2)).append("/");
        String pathen = tmpPath.toString();
        File directory = new File(pathen);

        boolean isDir = directory.mkdirs();
        return pathen.concat(uuid);
    }

    private void writeToFile(FileUploadForm form, String uploadedFileLocation) {

        try {
            File file = new File(uploadedFileLocation);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);

            fos.write(form.getFileData());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToDatabase(Media media) {
        String serverDate = bean.getServerDate();
        System.out.println("Media is -> " + media);
        System.out.println("Serverdate -> " + serverDate);
        bean.save(media);
    }

    private String getUUID() {
        final String uuIdFilename = UUID.randomUUID().toString();
        return uuIdFilename;
    }
}
