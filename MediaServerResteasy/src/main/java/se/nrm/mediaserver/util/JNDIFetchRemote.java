package se.nrm.mediaserver.util;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import se.nrm.mediaserver.service.MediaService;

/**
 *
 * @author ingimar
 */
public class JNDIFetchRemote {

    public static MediaService outreach() {
        MediaService bean = null;
        try {
            Properties jndiProps = new Properties();
            jndiProps.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
            jndiProps.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            jndiProps.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            jndiProps.setProperty("org.omg.CORBA.ORBInitialHost", "172.16.23.56"); // 192.168.10.163, 172.16.23.14, 172.16.23.13 192.168.10.163
            Context ctx = new InitialContext(jndiProps);
            String lookupEAR = "java:global/MediaserverApp-ear/MediaserverApp-ejb-1.0-SNAPSHOT/MediaServiceBean!se.nrm.mediaserver.service.MediaService";
            String lookupEJB = "java:global/MediaserverApp-ejb/MediaServiceBean!se.nrm.mediaserver.service.MediaService";
            bean = (MediaService) ctx.lookup(lookupEJB);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
