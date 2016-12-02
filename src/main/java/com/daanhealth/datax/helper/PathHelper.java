package com.daanhealth.datax.helper;

public class PathHelper {

    public static String getRunPath(String filename) {
        String path = PathHelper.class.getResource("/").getPath();
        path = path + filename;
        return path;
    }
}
