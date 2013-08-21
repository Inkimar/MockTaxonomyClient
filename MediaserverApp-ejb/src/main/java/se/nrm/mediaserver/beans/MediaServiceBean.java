package se.nrm.mediaserver.beans;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import se.nrm.mediaserver.service.MediaService;

/**
 * http://netbeans.dzone.com/articles/how-to-combine-rest-and-ejb-31
 * @author ingimar
 */
@Path("image")
@Stateless
public class MediaServiceBean implements Serializable, MediaService {

    @PersistenceContext(unitName = "MysqlPU")
    private EntityManager em;

    @GET
    @Produces("text/html")
    public String getHtml(){
        return "<h2>Hello "+this.getClass().getCanonicalName()+" </h2>";
    }
    
    @Override
    public String test() {
        System.out.println("Metoden test");
        String s = this.getClass().getCanonicalName();
        return s;
    }

    @Override
    public String getServerDate() {
        System.out.println("Metoden getServerDate");
        Date date = new Date();
        return "EJB-bean says Hello. Servertime is " + date.toString();
    }

    @Override
    public void save(Object media) {
        em.merge(media);
    }
}
