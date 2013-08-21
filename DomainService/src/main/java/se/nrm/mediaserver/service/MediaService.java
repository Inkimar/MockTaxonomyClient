/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.service;

import javax.ejb.Remote;

/**
 *
 * @author ingimar
 */
@Remote 
public interface MediaService {

    void save(Object media);
    String test();
    String getServerDate();
}
