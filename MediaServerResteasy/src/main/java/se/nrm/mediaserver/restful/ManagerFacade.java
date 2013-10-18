package se.nrm.mediaserver.restful;

/**
 *
 * @author ingimar
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.media3.domain.Media;
import se.nrm.mediaserver.service.MediaService;
import se.nrm.mediaserver.util.FilePropertiesHelper;
import se.nrm.mediaserver.util.JNDIFetchRemote;
import se.nrm.mediaserver.util.MimeParser;

@Path("/media")
public class ManagerFacade {

    MediaService bean = JNDIFetchRemote.outreach();

    @POST
    @Path("/upload-file")
    @Consumes("multipart/form-data")
    public Response uploadFile(@MultipartForm FileUploadForm form) {

        final String uuIdFilename = UUID.randomUUID().toString();

        String fileName = form.getFileName() == null ? "Unknown" : form.getFileName();
        String uploadedFileLocation = absolutePathToFile(uuIdFilename);
        writeToFile(form, uploadedFileLocation);

        File fileHandle = new File(uploadedFileLocation);
        String mimeType = "unkown";
        try {
            mimeType = MimeParser.getMimeFromFileContentAndExtension(fileHandle, uuIdFilename);

        } catch (IOException ioEx) {
            Logger.getLogger(ManagerFacade.class.getName()).log(Level.SEVERE, null, ioEx);
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
     * Exempelvis :
     * http://localhost:8080/MediaServerResteasy/media/9ed8a73d-d075-467b-b2cd-cd32acfbe90e
     * -Check the validity of the uuid
     * @param uuid
     * @return
     */
    @GET
    @Path("{uuid}")
    @Produces("text/plain")
    public String getMetaData(@PathParam("uuid") String uuid) {
       // check validity of uuid
        Image media = (Image) bean.get(uuid);
        System.out.println("Image is " + media);
        return media.toString();
    }

    // bryt ut. egen klass
    public String absolutePathToFile(String uuid) {
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
}
