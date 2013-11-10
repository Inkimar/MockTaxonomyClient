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
import se.nrm.mediaserver.util.FilePropertiesHelper;

//
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import se.nrm.mediaserver.util.PathHelper;

/**
 * http://localhost:8080/MediaServerResteasy/media/stream/84a6ad8b-3d9f-4f75-b856-44e23b002327
 * @author ingimar
 */
@Path("/media")
@Produces("image/png")
public class MediaResourceFetchService {

    @GET
    @Path("/stream/{uuid}")
    public Response getMedia(@PathParam("uuid") String fileName) {
        String dynPath = getDynamicPath(fileName);
        String file = dynPath.concat(fileName);

        File repositoryFile = new File(file);
        return returnFile(repositoryFile);
    }

    /**
     * testar en teknik, ska inte sparas ....
     *
     * @param fileName
     * @return
     */
    @GET
    @Path("/stream/small/{uuid}")
    public Response getMediaSmall(@PathParam("uuid") String fileName) {
        String dynPath = getDynamicPath(fileName);
        String file = dynPath.concat(fileName);
        System.out.println("filename "+fileName);
        // test to save, you could return the thumb without saving
        if (!file.endsWith(".thumb")) {
            savesThumbfil(file);
        }
        File repositoryFile = new File(file);
        return returnFile(repositoryFile);
    }

    private static Response returnFile(File file) {
        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            return Response.ok(new FileInputStream(file)).build();
        } catch (FileNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    private void savesThumbfil(String file) {

        
        // returnera on-the-fly , kan inte köra in BufferedImage hit ->  return Response.ok(new FileInputStream(file)).build();
        try {

            BufferedImage originalImage = ImageIO.read(new File(file));
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImageJpg = resizeImage150x150(originalImage, type);
            ImageIO.write(resizeImageJpg, "jpg", new File(file + ".thumb"));
            File cacheDirectory = ImageIO.getCacheDirectory();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getCause();
        }
    }

    /**
     * Todo kunna välja från ett urval av bilder,
     *
     * @param originalImage
     * @param type
     * @return
     */
    private static BufferedImage resizeImage150x150(BufferedImage originalImage, int type) {
        int IMG_WIDTH = 150;
        int IMG_HEIGHT = 150;
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;

    }

    private String getDynamicPath(String uuid) {
        return PathHelper.getDyanmicPathToFile(uuid);
    }
}
