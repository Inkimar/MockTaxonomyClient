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
import se.nrm.mediaserver.media3.domain.Image;

/**
 * (1) först Image , sen annan media - använd <T>
 *
 * @author ingimar
 */
public interface MediaResource {

    public Response createNewFile(@MultipartForm FileUploadForm form);

    public Image getMediaAsXML(@PathParam("uuid") String uuid);
    // public void putMediaMetaData(@PathParam("uuid") String uuid, @QueryParam("name") String name);

    public void deleteMediaMetaData(@PathParam("uuid") String uuid);
}