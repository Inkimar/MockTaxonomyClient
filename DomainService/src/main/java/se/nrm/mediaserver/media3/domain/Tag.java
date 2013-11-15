package se.nrm.mediaserver.media3.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ingimar
 */
@Entity
@Table(name = "TAG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll",
            query = "SELECT t FROM Tag t"),
    @NamedQuery(name = "Tag.findById",
            query = "SELECT t FROM Tag t WHERE t.id = :id"),
    @NamedQuery(name = "Tag.findByTagKey",
            query = "SELECT t FROM Tag t WHERE t.tagKey = :tagKey"),
    @NamedQuery(name = "Tag.findByTagPointer",
            query = "SELECT t FROM Tag t WHERE t.tagPointer = :tagPointer"),
    @NamedQuery(name = "Tag.findByExternalSystem",
            query = "SELECT t FROM Tag t WHERE t.externalSystem = :externalSystem")
//        ,
//    @NamedQuery(name = "Tag.findByDateCreated",
//            query = "SELECT t FROM Tag t WHERE t.dateCreated = :dateCreated")
})
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Size(max = 255)
    @Column(name = "TAG_KEY")
    private String tagKey;

    @Size(max = 245)
    @Column(name = "TAG_POINTER")
    private String tagPointer;

    @Size(max = 255)
    @Column(name = "EXTERNAL_SYSTEM")
    private String externalSystem;

//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "DATE_CREATED")
//    @Temporal(TemporalType.TIMESTAMP)
    @Transient
    private Date dateCreated;

    public Tag() {
    }

    public Tag(Integer id) {
        this.id = id;
    }

    public Tag(Integer id, Date dateCreated) {
        this.id = id;
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagKey() {
        return tagKey;
    }

    public void setTagKey(String tagKey) {
        this.tagKey = tagKey;
    }

    public String getTagPointer() {
        return tagPointer;
    }

    public void setTagPointer(String tagPointer) {
        this.tagPointer = tagPointer;
    }

    public String getExternalSystem() {
        return externalSystem;
    }

    public void setExternalSystem(String externalSystem) {
        this.externalSystem = externalSystem;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.nrm.mediaserver.media3.domain.Tag[ id=" + id + " ]";
    }

}
