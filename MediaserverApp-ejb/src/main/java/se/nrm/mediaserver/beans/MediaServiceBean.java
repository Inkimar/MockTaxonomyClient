package se.nrm.mediaserver.beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.nrm.mediaserver.beans.util.Profiled;
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.media3.domain.Media;
import se.nrm.mediaserver.media3.domain.Sound;
import se.nrm.mediaserver.media3.domain.Video;
import se.nrm.mediaserver.service.MediaService;

/**
 * http://netbeans.dzone.com/articles/how-to-combine-rest-and-ejb-31
 *
 * @author ingimar
 */
@Stateless
public class MediaServiceBean<T> implements MediaService<T>, Serializable {

    @PersistenceContext(unitName = "MysqlPU")
    private EntityManager em;

    /**
     * using an interceptor, @Profiled, to measure time.
     *
     * @param media
     */
    @Override
    @Profiled
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
        System.out.println("Again uuid " + uuid);
        Query namedQuery = em.createNamedQuery(Media.FIND_BY_UUID);
        namedQuery.setParameter("uuid", uuid);

        T image = (T) namedQuery.getSingleResult();

        return image;
    }

    /**
     * Should introduce 'paging', in case they are too many
     *
     * @return
     */
    @Override
    public List<Image> getAll() {
        Query query = em.createNamedQuery(Media.FIND_ALL);
        List<Image> images = query.getResultList();
        return images;
    }

    
    // @TODO , Slå ihop alla getAll till en ....
    @Override
    public List getAllImages() {
        Query query = em.createNamedQuery(Image.FIND_ALL_IMAGES);
        List<Image> images = query.getResultList();
        return images;
    }

    @Override
    public List getAllSounds() {
        Query query = em.createNamedQuery(Sound.FIND_ALL_SOUNDS);
        List<Sound> sounds = query.getResultList();
        return sounds;
    }

    @Override
    public List getAllVideos() {
        Query query = em.createNamedQuery(Video.FIND_ALL_VIDEOS);
        List<Video> videos = query.getResultList();
        return videos;
    }
    
    

    @Override
    public String getServerDate() {
        System.out.println("Metoden getServerDate");
        Date date = new Date();
        return "EJB-bean says Hello. Servertime is " + date.toString();
    }

}
