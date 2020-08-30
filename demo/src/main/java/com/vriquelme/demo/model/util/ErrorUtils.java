package com.vriquelme.demo.model.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vriquelme.demo.model.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private final static Logger logger = LoggerFactory.getLogger(ErrorUtils.class);

    public static String getError(String p_errorId, String p_message) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorId(p_errorId);
        errorDTO.setMessage(p_message);
        try {
            return objectMapper.writeValueAsString(errorDTO);
        } catch (JsonProcessingException e) {
            logger.info("Error in JsonProcessingException : " + e.toString());
        }
        return Constants.SPACE;
    }

}
