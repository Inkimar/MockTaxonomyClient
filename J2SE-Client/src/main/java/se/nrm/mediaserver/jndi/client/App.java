package se.nrm.mediaserver.jndi.client;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import se.nrm.mediaserver.service.MediaService;

/**
 * Not working at the moment .
 * Possible in an ANT-project 
 * ->> It seems that the key is the order of the JAR files in the classpath. Make sure you include gf-client.jar before javaee-api-6.0.jar really solves the problem for glassfish 3.1.1.
 * Using JNDI and a Remote interface: How to run : 1) Deploy the
 * MediaserverApp-ejb or the -ear 2) Check the IP-nr of the
 * 'MediaserverApp-ejb'-server 3) Enter that IP-nr in the fetchBean-method. 4)
 * Run this application
 *
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Start 11:38: Hej världen!");
        try {
            MediaService bean = fetchBean();
            System.out.println("Response coming from bean :->" + bean.test() + "<-");
        } catch (NamingException e) {
        }
        System.out.println("END: Hej världen!");
    }

    private static MediaService fetchBean() throws NamingException {
        Properties jndiProps = new Properties();
        String ipNr = "172.16.23.28";
        jndiProps.setProperty("org.omg.CORBA.ORBInitialHost", ipNr);

        Context ctx = new InitialContext(jndiProps);
        String lookupEJB = "java:global/MediaserverApp-ejb/MediaServiceBean!se.nrm.mediaserver.service.MediaService";
        String lookupShortEJB = "java:global/MediaserverApp-ejb/MediaServiceBean";
        String lookupnewEJB = "java:global/MediaServer/MediaServiceBean";
        MediaService bean = (MediaService) ctx.lookup(lookupnewEJB);
        return bean;
    }
}
