/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.service;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ingimar
 * @param <T>
 */
@Remote 
public interface MediaService<T extends Object> {

    void save(T media);
    void delete(String uuid); // might want the whole object
    T get(String uuid);
    String getServerDate();
    List getAll();

    public String getHtml();
}
