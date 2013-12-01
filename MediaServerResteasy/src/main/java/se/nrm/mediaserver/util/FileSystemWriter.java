/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @TODO, look at nio
 * @author ingimar
 */
public class FileSystemWriter implements Writeable{

    @Override
    public void writeBytesTo(byte[] data, String location) {
        try {
            File file = new File(location);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);

            fos.write(data);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
