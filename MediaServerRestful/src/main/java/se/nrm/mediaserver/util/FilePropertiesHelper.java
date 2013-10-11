package se.nrm.mediaserver.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reading from the property-file.
 *
 * @author ingimar
 */
public class FilePropertiesHelper {

    public static String getImagesFilePath() {

        String filePath = "";
        Properties properties = new Properties();

        try {
            InputStream iStream = getInputStream();
            properties.load(iStream);
            filePath = properties.getProperty("filepath.prefix.images");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }


//    private static String getDetailedFormattedDate() {
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        String formattedDate = format.format(date);
//        return formattedDate;
//    }
//    private static String getDate() {
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate = format.format(date);
//        return formattedDate;
//    }

    private static InputStream getInputStream() {
        InputStream iStream =
                FilePropertiesHelper.class.getClassLoader().getResourceAsStream("config.properties");
        return iStream;
    }
}
