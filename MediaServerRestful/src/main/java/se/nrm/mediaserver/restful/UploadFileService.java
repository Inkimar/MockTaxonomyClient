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
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.media3.domain.Media;
import se.nrm.mediaserver.service.MediaService;
import se.nrm.mediaserver.util.JNDIFetchRemote;

@Path("/file")
public class UploadFileService {

    MediaService bean = JNDIFetchRemote.outreach();

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("owner") String owner
            ) {

        final String uuIdFilename = UUID.randomUUID().toString();

        String uploadedFileLocation = "/opt/data/nf/vffmedia/" + uuIdFilename;
        writeToFile(uploadedInputStream, uploadedFileLocation);

        Media media = new Image();
        media.setUuid(uuIdFilename);
        media.setFilename(fileDetail.getFileName());
        media.setOwner(owner);
        media.setVisibility("public");
        writeToDatabase(media);

        String responseOutput = "File uploaded to : " + uploadedFileLocation;

        return Response.status(200).entity(responseOutput).build();

    }

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
        }
    }

    private void writeToDatabase(Media media) {
        String serverDate = bean.getServerDate();
        System.out.println("Media is -> " + media);
        System.out.println("Serverdate -> " + serverDate);
        bean.save(media);
    }
}
