package com.github.curriculeon.utils;

import java.io.File;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:58 PM
 * used to reference files from different one or more directories
 */
public enum DirectoryReference {
    MAIN_RESOURCE_DIRECTORY(new StringBuilder()
            .append(System.getProperty("user.dir")) // local directory
            .append("/src/main/resources/")
            .toString()),
    TEST_RESOURCE_DIRECTORY(new StringBuilder()
            .append(System.getProperty("user.dir")) // local directory
            .append("/src/test/resources/")
            .toString());;

    private final String path;

    DirectoryReference(String path) {
        this.path = path;
    }

    public File getDirectoryFile() {
        return new File(getDirectoryPath());
    }

    public String getDirectoryPath() {
        return path;
    }

    public File getFileFromDirectory(String fileName) {
        return new File(getDirectoryPath() + fileName);
    }
}
