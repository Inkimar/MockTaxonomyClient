/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.service;

import javax.ejb.Local;

/**
 *
 * @author ingimar
 */
@Local
public interface MediaLocalService {
    void saveLocal(Object media);
    String testLocal();
    String getLocalServerDate();
}
