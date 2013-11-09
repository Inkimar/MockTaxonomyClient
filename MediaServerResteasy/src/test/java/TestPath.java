/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.nrm.mediaserver.util.FilePropertiesHelper;

/**
 *
 * @author ingimar
 */
public class TestPath {

    public TestPath() {
    }

    @Test
    public void hello() {
        String uuid = "8ab00854-849b-4f32-8f9e-6a2b00d9da71";
        final String IMAGE_PATH = FilePropertiesHelper.getImagesFilePath();
        StringBuilder tmpPath = new StringBuilder(IMAGE_PATH);
        tmpPath.append(uuid.charAt(0)).append("/").append(uuid.charAt(1)).append("/").append(uuid.charAt(2)).append("/");
        String pathen = tmpPath.toString();

        assertEquals("/opt/data/mediaserver/newmedia/", IMAGE_PATH);
        System.out.println("pathen "+pathen);
        assertEquals("/opt/data/mediaserver/newmedia/8/a/b/", pathen);
    }
}
