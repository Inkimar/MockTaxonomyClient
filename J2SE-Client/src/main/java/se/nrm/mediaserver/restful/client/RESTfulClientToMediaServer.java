package se.nrm.mediaserver.restful.client;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import se.nrm.mediaserver.media3.domain.*;
import se.nrm.mediaserver.restful.util.RestfulImage;
import se.nrm.mediaserver.restful.util.URI;

/**
 * When running the server = 'MediaServer' - The MediaServer-Project is
 * old-School ( Using web.xml )
 *
 * @author ingimar
 */
public class RESTfulClientToMediaServer {

    public static void main(String args[]) throws UniformInterfaceException {
        System.out.println("Client calling Restful-service at MediaServer .... ");
        createImage();
    }

    /**
     * Decide which Restful-service you want to use WebResource resource =
     * URI.getMediaWebResource(); // which resource
     *
     * @throws UniformInterfaceException
     */
    private static void createImage() throws UniformInterfaceException {
        WebResource resource = URI.getMediaWebResource(); // which resource
        RestfulImage restful = new RestfulImage(resource);

        Media media = new Image();

        media.setFilename("20aug-16:26.jpg");
        media.setOwner("inki");
        media.setVisibility("public");
        System.out.println("Media:Image " + media);
        restful.create_XML(media);
    }
}
