/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.restful;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

//
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tika.Tika;
import se.nrm.mediaserver.util.PathHelper;

/**
 * http://localhost:8080/MediaServerResteasy/media/stream/84a6ad8b-3d9f-4f75-b856-44e23b002327
 *
 * @author ingimar
 */
@Path("/media")
@Produces("image/png")
public class MediaResourceFetchBinary {

    @GET
    @Path("/stream/{uuid}")
    public Response getMedia(@PathParam("uuid") String fileName) {
        String dynPath = getDynamicPath(fileName);
        String file = dynPath.concat(fileName);

        File repositoryFile = new File(file);
        return returnFile(repositoryFile);
    }

    @GET
    @Path("/streaming/{uuid}")
    @Produces("image/jpeg")
    public byte[] getT(@PathParam("uuid") String uuid) {
        System.out.println("uuid " + uuid);
        String dynPath = getDynamicPath(uuid);
        String filename = dynPath.concat(uuid);

        File fileHandle = new File(filename);
        ByteArrayOutputStream bo = new ByteArrayOutputStream(2048);

        try {
            BufferedImage originalImage = ImageIO.read(fileHandle);
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage transformedImage = Thumbnails.of(originalImage).size(150, 150).asBufferedImage();
            ImageIO.write(transformedImage, "jpeg", bo);
        } catch (IOException ex) {
            Logger.getLogger(MediaResourceFetchBinary.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bo.toByteArray();
    }

    private static Response returnFile(File file) {

        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            Tika tika = new Tika();
            String mimeType = tika.detect(file);
            return Response.ok(new FileInputStream(file), mimeType).build();
        } catch (IOException ioEx) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    private String getDynamicPath(String uuid) {
        return PathHelper.getDyanmicPathToFile(uuid);
    }
}
