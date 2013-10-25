package se.nrm.mediaserver.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import se.nrm.mediaserver.media3.domain.Image;
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
        em.merge(media);
    }
    
    @Override
    public void delete(String uuid) {
       T fetchedEntity = get(uuid);
       em.remove(fetchedEntity);
    }

    @Override
    public T get(String uuid) {
        T Image = null;
        final String fetch = "SELECT c FROM Image c  where c.uuid = :uuid";
        Query createQuery = em.createQuery(fetch);
        createQuery.setParameter("uuid", uuid);
        Image = (T) createQuery.getSingleResult();

        return Image;
    }

    @Override
    public List<Image> getAll() {
        Query query = em.createNamedQuery(Image.FIND_ALL);
        List<Image> images = query.getResultList();
        return images;
    }
    
    

    
}
