package com.devtools.assignment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class PropertyParsingService {

    public static final String XML = "xml";

    public static final String JSON = "json";

    private static final Logger logger = LoggerFactory.getLogger(PropertyParsingService.class);

    public String propertiesToJson(Properties properties){

        logger.debug("Starting JSON parsing process...");
        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.writeValueAsString(properties);
        } catch (JsonProcessingException e){
            logger.error("Some issues found. Caused: [{}]", e.getMessage());
            return "";
        }
    }

    public String propertiesToXml(Properties properties){
        logger.debug("Starting XML parsing process...");
        XmlMapper mapper = new XmlMapper();

        try{
            return mapper.writeValueAsString(properties);
        } catch (JsonProcessingException e){
            logger.error("Some issues found. Caused: [{}]", e.getMessage());
            return "";
        }
    }
}
