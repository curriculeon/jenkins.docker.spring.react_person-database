package com.github.curriculeon.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by leon on 12/22/2020.
 */
public class ApplicationProperties {
    private PropertiesConfiguration config;

    public ApplicationProperties(PropertiesConfiguration config, DirectoryReference directoryReference, String fileName) {
        this.config = config;
        try {
            config.load(directoryReference.getFileFromDirectory(fileName));
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public ApplicationProperties(DirectoryReference directoryReference, String fileName) {
        this(new PropertiesConfiguration(), directoryReference, fileName);
    }

    public ApplicationProperties(String fileName) {
        this(DirectoryReference.MAIN_RESOURCE_DIRECTORY, fileName);
    }

    public ApplicationProperties() {
        this("application.properties");
    }

    public PropertiesConfiguration getConfig() {
        return config;
    }
}
