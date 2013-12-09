package se.nrm.mediaserver.restful;

/**
 *
 * @author ingimar
 */
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.service.MediaService;
import se.nrm.mediaserver.util.JNDIFetchRemote;

/**
 * @author ingimar
 */
@Path("/media")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public class MediaResourceFetchMetaData implements MediaResource {

    @Context
    private UriInfo uriInfo;

    final MediaService bean = JNDIFetchRemote.outreach();

    /**
     * Fetch metadata from database
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

    @GET
    @Produces("text/plain")
    public String getTesting() {
        long millis = System.currentTimeMillis() % 1000;
        return "Testar ... "+millis;
    }
}
