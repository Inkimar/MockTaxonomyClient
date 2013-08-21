/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.restful;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.media3.domain.Media;
import se.nrm.mediaserver.service.MediaService;
import se.nrm.mediaserver.util.JNDIFetchRemote;

/**
 * 1) Dependent upon Domain-project, using @Remote-interface.
 *
 * http://localhost:8080/MediaServerRestful/resources/media
 *
 * @author ingimar
 */
@Stateless
@Path("/media")
public class MediaManagerFacade {

    // Just nu remote.
    MediaService bean = JNDIFetchRemote.outreach();

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Image media) {
       bean.save(media);
    }
    
    @GET
    @Produces("text/html")
    public String getXml() {
//        Media media = this.getMedia();
//        bean.save(media);
        String serverDate = bean.getServerDate();
        return "<html><body><h1>Hello Ingimar " + serverDate + " </h1></body></html>";
    }

    private Media getMedia() {
        Media media = new Image();
        media.setFilename("20augusti-14:25-restful.jpg");
        media.setOwner("Larssons");
        return media;
    }
}
