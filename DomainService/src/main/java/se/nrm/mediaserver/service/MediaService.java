/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.service;

import java.util.List;
import javax.ejb.Remote;

/**
 * Concerns only the database
 * @author ingimar
 * @param <T>
 */
@Remote 
public interface MediaService<T extends Object> {

    void save(T media);
    void delete(String uuid); // might want the whole object ?
    T get(String uuid);
    List getAll();
    String getServerDate();
}
