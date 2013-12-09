/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.media3.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ingimar
 */
@Entity
@Table(name = "DETERMINATION")
@NamedQueries({
    @NamedQuery(name = "Determination.findAll", query = "SELECT d FROM Determination d"),
    @NamedQuery(name = "Determination.findById", query = "SELECT d FROM Determination d WHERE d.id = :id"),
    @NamedQuery(name = "Determination.findByTagKey", query = "SELECT d FROM Determination d WHERE d.tagKey = :tagKey"),
    @NamedQuery(name = "Determination.findByTagValue", query = "SELECT d FROM Determination d WHERE d.tagValue = :tagValue"),
    @NamedQuery(name = "Determination.findByExternalSystem", query = "SELECT d FROM Determination d WHERE d.externalSystem = :externalSystem"),
    @NamedQuery(name = "Determination.findByExternalSystemUrl", query = "SELECT d FROM Determination d WHERE d.externalSystemUrl = :externalSystemUrl")
    //    @NamedQuery(name = "Determination.findByDateCreated", query = "SELECT d FROM Determination d WHERE d.dateCreated = :dateCreated")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Determination implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Size(max = 255)
    @Column(name = "TAG_KEY")
    private String tagKey;

    @Size(max = 255)
    @Column(name = "TAG_VALUE")
    private String tagValue;

    @Size(max = 255)
    @Column(name = "EXTERNAL_SYSTEM")
    private String externalSystem;

    @Size(max = 255)
    @Column(name = "EXTERNAL_SYSTEM_URL")
    private String externalSystemUrl;

    @Transient
    private Date dateCreated;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MEDIA_UUID")
    @XmlTransient
    private Media mediaUuid;

    public Determination() {
    }

    public Determination(Integer id) {
        this.id = id;
    }

//    public Determination(Integer id, Date dateCreated) {
//        this.id = id;
//        this.dateCreated = dateCreated;
//    }
    public Determination(String tagKey, String tagValue, String externalSystem, String externalSystemUrl, Media mediaUuid) {
        this.tagKey = tagKey;
        this.tagValue = tagValue;
        this.externalSystem = externalSystem;
        this.externalSystemUrl = externalSystemUrl;
        this.mediaUuid = mediaUuid;
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

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public String getExternalSystem() {
        return externalSystem;
    }

    public void setExternalSystem(String externalSystem) {
        this.externalSystem = externalSystem;
    }

    public String getExternalSystemUrl() {
        return externalSystemUrl;
    }

    public void setExternalSystemUrl(String externalSystemUrl) {
        this.externalSystemUrl = externalSystemUrl;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Media getMediaUuid() {
        return mediaUuid;
    }

    public void setMediaUuid(Media mediaUuid) {
        this.mediaUuid = mediaUuid;
    }

    @Override
    public String toString() {
        return "se.nrm.mediaserver.media3.domain.Determination[ id=" + id + " ]";
    }

}
