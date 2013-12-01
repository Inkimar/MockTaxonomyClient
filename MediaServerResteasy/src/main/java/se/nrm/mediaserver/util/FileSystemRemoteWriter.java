/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.util;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Node;
import javax.jcr.SimpleCredentials;
import javax.jcr.Workspace;
import org.apache.jackrabbit.rmi.repository.URLRemoteRepository;

/**
 *
 * @author ingimar
 */
public class FileSystemRemoteWriter implements Writeable {

    /**
     * Testing, probably not the way to go.
     * http://jackrabbit.apache.org/first-hops.html
     *
     * @param data
     * @param location
     */
    @Override
    public void writeBytesTo(byte[] data, String location) {
//        String remoteLocation = "http://localhost:18080/repository/default/";
//        try {
//            URLRemoteRepository repo = new URLRemoteRepository(remoteLocation);
//            SimpleCredentials creds = new SimpleCredentials("admin", "admin".toCharArray());
//            Session session = repo.login(creds, "default");
////            Session session = repo.login( new SimpleCredentials("username", "password".toCharArray()));
//            Node root = session.getRootNode();
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(FileSystemRemoteWriter.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (RepositoryException ex) {
//            Logger.getLogger(FileSystemRemoteWriter.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

}
