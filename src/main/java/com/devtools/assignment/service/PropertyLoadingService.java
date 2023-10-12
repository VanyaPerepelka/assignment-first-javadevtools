package com.devtools.assignment.service;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

@Getter
public class PropertyLoadingService {

    private static final Logger logger = LoggerFactory.getLogger(PropertyLoadingService.class);

    /**
     * Simple getter of JAR placing directory using File.getCanonicalPath
     * @return path to JAR in format "jar/placing/dir"
     * */
    private String getJarDirectory(){
        try{
            return Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
        } catch (NullPointerException e){
            logger.error("JAR locating issues. Cased: {}", e.getMessage());
            return null;
        }

    }

    /**
     * Method to locate property file that placed near running JAR on the same directory
     * @param propertyFileName name of file that should be parsed
     * @return filled with parsed data Property object if exists, empty object otherwise
     * @see Properties
     * @see PropertyLoadingService
     * */
    public Properties loadJarNeighbourProperties(String propertyFileName) {

        logger.debug("Starting parsing a property file with name [{}]...", propertyFileName);
        Properties props = new Properties();

        logger.debug("Trying to get JAR current directory...");
        String jarWorkingDirectory = getJarDirectory();

        if (jarWorkingDirectory != null){
            logger.info("JAR path found: [{}]", jarWorkingDirectory);

            String propertyFilePath = jarWorkingDirectory.concat(propertyFileName);

            logger.info("Property file path was generated: [{}]", propertyFilePath);

            try (var inputStream = new InputStreamReader(new FileInputStream(propertyFilePath), StandardCharsets.UTF_8)) {
                props.load(inputStream);
                logger.info("Property file parsed successfully: {}", props);

            } catch (IOException e) {
                logger.error("Property file locating issues. Caused: {}", e.getMessage());
            }
        }
        return props;
    }

}

