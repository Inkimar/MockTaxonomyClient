package se.nrm.mediaserver.beans;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import se.nrm.mediaserver.service.MediaService;

/**
 * http://netbeans.dzone.com/articles/how-to-combine-rest-and-ejb-31
 *
 * @author ingimar
 */
@Path("image")
@Stateless
public class MediaServiceBean<T> implements MediaService<T>, Serializable {

    @PersistenceContext(unitName = "MysqlPU")
    private EntityManager em;

    @GET
    @Produces("text/html")
    public String getHtml() {
        return "<h2>Hello " + this.getClass().getCanonicalName() + " </h2>";
    }

    @Override
    public String getServerDate() {
        System.out.println("Metoden getServerDate");
        Date date = new Date();
        return "EJB-bean says Hello. Servertime is " + date.toString();
    }

    @Override
    public void save(T media) {
        System.out.println("saving now: 12:50");
        em.merge(media);
    }

    @Override
    public T get(String uuid) {
        T Image = null;
        // final String fetch = "SELECT c FROM Media c  where c.uuid = :uuid";
        final String fetch = "SELECT c FROM Image c  where c.uuid = :uuid";
        Query createQuery = em.createQuery(fetch);
        createQuery.setParameter("uuid", uuid);
        Image = (T) createQuery.getSingleResult();

        return Image;
    }
}
