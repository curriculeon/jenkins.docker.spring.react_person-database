package com.github.curriculeon.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/12/2020 - 6:26 PM
 * used to convert files contents to String
 */
public class FileReader {
    private final String filename;

    public FileReader(String filename) {
        this.filename = filename;
    }

    public String getLine(int number) {
        return getLines().get(number);
    }

    public List<String> getLines() {
        return Arrays.asList(toString().split("\n"));
    }

    @Override
    public String toString() {
        try {
            byte[] readAllBytes = Files.readAllBytes(Paths.get(filename));
            return new String(readAllBytes);
        } catch (IOException e) {
            throw new Error(e);
        }
    }
}

