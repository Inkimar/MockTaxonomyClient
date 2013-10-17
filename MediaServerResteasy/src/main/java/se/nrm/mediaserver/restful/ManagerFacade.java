/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
        
        dummy();

        return Response.status(200).entity(responseOutput).build();
    }

    private void dummy() {
        final String uuid = "e3e2e57f-67fe-4644-a995-0fe25db1e48a";
        Image media = (Image) bean.get(uuid);
        System.out.println("Image is " + media);
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
        // bean.insert(media);
    }
}
