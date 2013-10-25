/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.media3.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Used to test integration between the Modules, removed later.
 * @author ingimar
 */
@Entity
@XmlRootElement(name = "bubble")
public class Bubble implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Float price;

    private String description;

    private String isbn;

    private Integer nbOfPage;

    private Boolean illustrations;

    public Bubble() {
    }

    public Bubble(String title) {
        this.title = title;
    }
    
    public Bubble(String title, String description, Boolean illustrations, 
            String isbn, Integer nbOfPage, Float price) {
        this.title = title;
        this.description = description;
        this.illustrations = illustrations;
        this.isbn = isbn;
        this.nbOfPage = nbOfPage;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNbOfPage() {
        return nbOfPage;
    }

    public void setNbOfPage(Integer nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Book");
        sb.append(", id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", nbOfPage=").append(nbOfPage);
        sb.append(", illustrations=").append(illustrations);
        sb.append('}');
        return sb.toString();
    }
}
