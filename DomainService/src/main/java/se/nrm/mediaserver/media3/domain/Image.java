package se.nrm.mediaserver.media3.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ingimar
 */
@Entity
@Table(name = "IMAGE")
@NamedQuery(name = Image.FIND_ALL, query = "SELECT i FROM Image i")
@XmlRootElement
public class Image extends Media implements Serializable {

    private static final long serialVersionUID = 6L;
    
    public static final String FIND_ALL = "Image.findAll";

    public Image() {
    }

    public Image(String owner) {
        super(owner);
    }
    
    
    public Image(String owner, String visibility, String filename, String mimetype) {
      super(owner, visibility, filename, mimetype);
    }  
}
