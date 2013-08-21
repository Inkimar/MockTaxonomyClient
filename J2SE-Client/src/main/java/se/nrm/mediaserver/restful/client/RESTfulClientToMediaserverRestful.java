/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.restful.client;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.media3.domain.Media;
import se.nrm.mediaserver.restful.util.RestfulImage;
import se.nrm.mediaserver.restful.util.URI;

/**
 *
 * @author ingimar
 */
public class RESTfulClientToMediaserverRestful {
     public static void main(String args[]) throws UniformInterfaceException {
        System.out.println("Client calling Restful-service at MediaServerRestful .... ");
        createImage();
    }

    /**
     * Decide which Restful-service you want to use WebResource resource =
     * URI.getMediaWebResource(); // which resource
     *
     * @throws UniformInterfaceException
     */
    private static void createImage() throws UniformInterfaceException {
        WebResource resource = URI.getMediaWebResource();
        RestfulImage restful = new RestfulImage(resource);

        Media media = new Image();

        media.setFilename("21aug-19:48.jpg");
        media.setOwner("inki");
        media.setVisibility("public");
        System.out.println("Media:Image " + media);
        restful.create_XML(media);
    }
}
