/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.restful;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import se.nrm.mediaserver.media3.domain.Attachment;
import se.nrm.mediaserver.media3.domain.Determination;
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.media3.domain.Sound;
import se.nrm.mediaserver.media3.domain.Video;

/**
 *
 * @author ingimar
 */
public interface MediaResource {

   // public Response createNewFile(@MultipartForm FileUploadForm form);

    public Image getImageAsXML(@PathParam("uuid") String uuid);
    public Sound getSoundAsXML(@PathParam("uuid") String uuid);
    public Video getVideoAsXML(@PathParam("uuid") String uuid);
    public Attachment getAttachmentAsXML(@PathParam("uuid") String uuid);
    
    // public void putMediaMetaData(@PathParam("uuid") String uuid, @QueryParam("name") String name);

    public void deleteMediaMetaData(@PathParam("uuid") String uuid);
    
    
    public Determination getDeterminationAsXML(@PathParam("extuuid") String extuuid);
}
