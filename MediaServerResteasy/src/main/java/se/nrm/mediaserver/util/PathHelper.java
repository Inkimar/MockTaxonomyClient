package se.nrm.mediaserver.util;

import java.io.File;

/**
 *
 * @author ingimar
 */
public class PathHelper {

    /**
     * Creates the directory and saves the file.
     * @param uuid
     * @return 
     */
    public static String getDynamicUUUIDFile(String uuid) {
        String pathen = getPath(uuid);
        File directory = new File(pathen);
        boolean isDir = directory.mkdirs();
        return pathen.concat(uuid);
    }

    public static String getDyanmicPathToFile(String uuid) {
        String pathen = getPath(uuid);
        return pathen;
    }

    private static String getPath(String uuid) {
        final String IMAGE_PATH = getAbsPath();
        StringBuilder tmpPath = new StringBuilder(IMAGE_PATH);
        tmpPath.append(uuid.charAt(0)).append("/").append(uuid.charAt(1)).append("/").append(uuid.charAt(2)).append("/");
        String pathen = tmpPath.toString();
        return pathen;
    }

    private static String getAbsPath() {
        final String IMAGE_PATH = FilePropertiesHelper.getImagesFilePath();
        return IMAGE_PATH;
    }
}