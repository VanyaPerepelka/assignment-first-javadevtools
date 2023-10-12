package com.devtools.assignment;

import com.devtools.assignment.service.PropertyLoadingService;
import com.devtools.assignment.service.PropertyParsingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    static PropertyLoadingService loadingService;
    static PropertyParsingService parsingService;

    static Properties properties;

    public static void main(String[] args){
        loadingService = new PropertyLoadingService();
        parsingService = new PropertyParsingService();
        loadProperties(args[0]);
        logPropertyMessages(args[1]);
    }

    private static void loadProperties(String fileName){
        if (fileName.equals("")){
            logger.info("File name is no provided");
            properties = new Properties();
        } else {
            properties = loadingService.loadJarNeighbourProperties(fileName);
        }

    }

    private static void logPropertyMessages(String format){
        String msg;

        if (format.equals(PropertyParsingService.XML)){
            msg = parsingService.propertiesToXml(properties);
            logger.info("Here is parsed props in XML format: [{}]", msg);
        }

        if (format.equals(PropertyParsingService.JSON)){
            msg = parsingService.propertiesToJson(properties);
            logger.info("Here is parsed props in JSON format: [{}]", msg);
        }

        if (format.equals("")){
            logger.info("Format is no provided. Loading raw property file...");
            logger.info("Property file: [{}]", properties);
        }
    }
}