/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.util;

/**
 *
 * @author ingimar
 */
public interface Writeable {
      public void writeBytesTo(byte[] data, String location) ;
}
