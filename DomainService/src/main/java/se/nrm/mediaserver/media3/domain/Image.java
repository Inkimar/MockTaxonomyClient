package se.nrm.mediaserver.media3.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ingimar
 */
@Entity
@Table(name = "IMAGE")
@XmlRootElement
public class Image extends Media implements Serializable {

    private static final long serialVersionUID = 6L;

    public Image() {
    }
   
    
}
