package se.nrm.mediaserver.media3.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Will wait to implement hashcode/equals. hascode and equals ...
 * http://stackoverflow.com/questions/5031614/the-jpa-hashcode-equals-dilemma //
 * http://michalorman.com/2012/07/do-not-override-equals-and-hashcode-for-entities/
 *
 * @author ingimar
 */
@Entity
@Table(name = "MEDIA")
@NamedQueries({
    @NamedQuery(name = Media.FIND_BY_UUID, query = "SELECT c FROM Media c  where c.uuid = :uuid")
})
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Media implements Serializable {

    private static final long serialVersionUID = -863604661852460140L;

    public static final String FIND_BY_UUID = "Media.FindByUuid";

    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "OWNER", length = 255, table = "MEDIA")
    private String owner;

    @Column(name = "VISIBILITY", length = 50, table = "MEDIA")
    private String visibility;

    @Column(name = "FILENAME", length = 255, table = "MEDIA")
    private String filename;

    @Column(name = "MIME_TYE", length = 50, table = "MEDIA")
    private String mimetype; // anv. Enum

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "media",
            targetEntity = Tag.class, fetch = FetchType.EAGER)
    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag", required = true)
    private Collection<Tag> tags;

//    @Embedded
//    private MediaText mediaText;
    public Media() {
    }

    public Media(String owner) {
        this.owner = owner;
    }

    /**
     * Testing with JSON from curl.
     *
     * @param owner
     * @param visibility
     * @param filename
     * @param mimetype
     */
    public Media(String owner, String visibility, String filename, String mimetype) {
        this.owner = owner;
        this.visibility = visibility;
        this.filename = filename;
        this.mimetype = mimetype;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getTest() {
        return "testing";
    }

    public Collection<Tag> getTags() {
        if (tags != null) {
            return Collections.unmodifiableCollection(tags);
        } else {
            return Collections.<Tag>emptyList();
        }
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

//    public List<Tag> getTags() {
//        if (tags != null) {
//            tags.size(); // this is needed for IndirectList: not instantiated 
//            return Collections.unmodifiableList(tags);
//        } else {
//            return Collections.<Tag>emptyList();
//        }
//    }
//
//    public boolean addTag(Tag tag) {
//        if (tags == null) {
//            tags = new LinkedList<Tag>();
//        }
//        if (tag != null && !tags.contains(tag)) {
//            tags.add(tag);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean removeTag(Tag tag) {
//        return (tags != null && !tags.isEmpty() && tags.remove(tag));
//    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("Media:");
        sb.append(", uuid='").append(uuid).append('\'');
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", visibility='").append(visibility).append('\'');
        sb.append(", filename='").append(filename).append('\'');
        sb.append(", mimetype='").append(mimetype).append('\'');
        // for the sake of testing
        if (tags != null) {
            sb.append(", tag size ='").append(tags.size()).append('\'');
        }
        sb.append('}');
        return sb.toString();
    }
}
