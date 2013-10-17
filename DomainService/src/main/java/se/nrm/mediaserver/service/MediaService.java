/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.service;

import javax.ejb.Remote;

/**
 *
 * @author ingimar
 * @param <T>
 */
@Remote 
public interface MediaService <T extends Object> {

    void save(T media);
    String getServerDate();
}
