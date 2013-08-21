/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.restful.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 *
 * @author ingimar
 */
public class URI {

    public static WebResource getMediaWebResource() {
        final String BASE_URI_R = "http://localhost:8080/MediaServerRestful/resources";
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(BASE_URI_R).path("media");

        return webResource;
    }

    public static WebResource getMediaWebResourceForMediaServer() {
        final String BASE_URI = "http://localhost:8080/MediaServer/webresources";
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(BASE_URI).path("se.mediaserver.tutorial.domain.image");

        return webResource;
    }
}
