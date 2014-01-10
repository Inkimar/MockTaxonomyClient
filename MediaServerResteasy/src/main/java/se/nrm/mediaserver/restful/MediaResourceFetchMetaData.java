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
import se.nrm.mediaserver.media3.domain.Attachment;
import se.nrm.mediaserver.media3.domain.Determination;
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.media3.domain.Sound;
import se.nrm.mediaserver.media3.domain.Video;
import se.nrm.mediaserver.service.MediaService;
import se.nrm.mediaserver.util.JNDIFetchRemote;

/**
 * http://localhost:8080/MediaServerResteasy/media/image/18ac3829-49bd-42ed-a975-0ba839167f33
 *
 * @author ingimar
 */
@Path("/media")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public class MediaResourceFetchMetaData implements MediaResource {

    @Context
    private UriInfo uriInfo;

    final MediaService bean = JNDIFetchRemote.outreach();

    @GET
    @Path("/image/{uuid}")
    @Override
    public Image getImageAsXML(@PathParam("uuid") String uuid) {
        Image image = (Image) bean.get(uuid);
        return image;
    }

    @GET
    @Path("/determination/{extuuid}")
    @Override
    public Determination getDeterminationAsXML(@PathParam("extuuid") String extUUID) {
        Determination determination = (Determination) bean.getDetermination(extUUID);
        return determination;
    }

    @GET
    @Path("/video/{uuid}")
    @Override
    public Video getVideoAsXML(@PathParam("uuid") String uuid) {
        Video video = (Video) bean.get(uuid);
        return video;
    }

    @GET
    @Path("/sound/{uuid}")
    @Override
    public Sound getSoundAsXML(@PathParam("uuid") String uuid) {
        Sound sound = (Sound) bean.get(uuid);
        return sound;
    }

    @GET
    @Path("/attachment/{uuid}")
    @Override
    public Attachment getAttachmentAsXML(@PathParam("uuid") String uuid) {
        Attachment attachment = (Attachment) bean.get(uuid);
        return attachment;
    }

    @GET
    @Path("/all")
    @Produces("text/plain")
    public List getAllMediaMetaData() {
        List<Image> images = bean.getAll();
        return images;
    }

    @GET
    @Path("/allImages")
    @Produces("text/plain")
    public List getAllImagesMetaData() {
        List<Image> images = bean.getAllImages();
        return images;
    }

    @GET
    @Path("/allVideos")
    @Produces("text/plain")
    public List getAllVideosMetaData() {
        List<Video> videos = bean.getAllVideos();
        return videos;
    }

    // <editor-fold defaultstate="collapsed" desc="@Deletion, not for now">
    @DELETE
    @Path("/image/{uuid}")
    @Override
    public void deleteMediaMetaData(@PathParam("uuid") String uuid) {
        bean.delete(uuid);
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Testing purposes">
    @GET
    @Path("/test")
    @Produces("text/plain")
    public String getTesting() {
        long millis = System.currentTimeMillis() % 1000;
        return "Testar ... " + millis;
    }
// </editor-fold>

}
