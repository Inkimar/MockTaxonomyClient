package se.nrm.mediaserver.restful;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.media3.domain.Media;
import se.nrm.mediaserver.service.MediaService;
import se.nrm.mediaserver.util.FilePropertiesHelper;
import se.nrm.mediaserver.util.JNDIFetchRemote;
import se.nrm.mediaserver.util.MimeParser;

@Path("/tmp")
public class TmpUpload {

    MediaService bean = JNDIFetchRemote.outreach();

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("json") String json
            ) {

        final String uuIdFilename = UUID.randomUUID().toString();

        String uploadedFileLocation = absolutePathToFile(uuIdFilename);
        writeToFile(uploadedInputStream, uploadedFileLocation);

        File fileHandle = new File(uploadedFileLocation);
        String mimeType ="unkown" ;
        try {
           mimeType = MimeParser.getMimeFromFileContentAndExtension(fileHandle, uuIdFilename);

        } catch (IOException ioEx) {
            Logger.getLogger(UploadFileService.class.getName()).log(Level.SEVERE, null, ioEx);
        }

        // beroende vilken Media det är ...
        Media media = new Image();
        media.setUuid(uuIdFilename);
        media.setFilename(fileDetail.getFileName());
        media.setMimetype(mimeType);
        media.setOwner(json);
        media.setVisibility(json);
        
        writeToDatabase(media);

        String responseOutput = "File uploaded to : " + uploadedFileLocation;

        return Response.status(200).entity(responseOutput).build();

    }

    //@TODO, check filelocation, nio?
    private void writeToFile(InputStream uploadedInputStream,
            String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(
                    uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            
        }
    }

    public String absolutePathToFile(String uuid) {
        String IMAGE_PATH = FilePropertiesHelper.getImagesFilePath();

        StringBuilder tmpPath = new StringBuilder(IMAGE_PATH);
        tmpPath.append(uuid.charAt(0)).append("/").append(uuid.charAt(1)).append("/").append(uuid.charAt(2)).append("/");
        String pathen = tmpPath.toString();
        File directory = new File(pathen);

        boolean isDir = directory.mkdirs();
        return pathen.concat(uuid);
    }

    private void writeToDatabase(Media media) {
        String serverDate = bean.getServerDate();
        System.out.println("Media is -> " + media);
        System.out.println("Serverdate -> " + serverDate);
        bean.save(media);
    }
}

