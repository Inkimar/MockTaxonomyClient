package se.nrm.mediaserver.restful;

/**
 *
 * @author ingimar
 */
import java.io.File;
import java.io.IOException;
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
import javax.ws.rs.core.UriInfo;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.media3.domain.Media;
import se.nrm.mediaserver.service.MediaService;
import se.nrm.mediaserver.util.JNDIFetchRemote;
import se.nrm.mediaserver.util.MimeParser;
import se.nrm.mediaserver.util.PathHelper;
import se.nrm.mediaserver.util.FileSystemWriter;
import se.nrm.mediaserver.util.Writeable;

/**
 * @author ingimar
 */
@Path("/media")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public class MediaResourceService implements MediaResource {

    @Context
    private UriInfo uriInfo;

    final MediaService bean = JNDIFetchRemote.outreach();

    /**
     * Upload file and metadata via a form / see index.jsp Saves file to
     * filesystem, metadata to database.
     * dependency upon resteasy - a restful impl.
     * @param form
     * @return
     */
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

    /**
     * Fetch metadata from database, using the EJB
     *
     * @param uuid
     * @return
     */
    @GET
    @Path("/image/{uuid}")
    @Override
    public Image getMediaAsXML(@PathParam("uuid") String uuid) {
        Image image = (Image) bean.get(uuid);
        System.out.println("Image is " + image);
        return image;
    }

    /**
     * @param uuid
     */
    @DELETE
    @Path("/image/{uuid}")
    @Override
    public void deleteMediaMetaData(@PathParam("uuid") String uuid) {
        bean.delete(uuid);
    }

    @GET
    @Path("/all")
    @Produces("text/plain")
    public List getAllMediaMetaData() {
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

    private String absolutePathToFile(String uuid) {
        return PathHelper.getDynamicUUUIDFile(uuid);
    }

    // @TODO Should be the responsibility of the bean.
    private void writeToFile(FileUploadForm form, String location) {

        Writeable writer = new FileSystemWriter();
        System.out.println("testing ");
        writer.writeBytesTo(form.getFileData(), location);
    }

    // dispatched to bean
    private void writeToDatabase(Media media) {
        String serverDate = bean.getServerDate();
        bean.save(media);
    }

    private String getUUID() {
        final String uuIdFilename = UUID.randomUUID().toString();
        return uuIdFilename;
    }
}