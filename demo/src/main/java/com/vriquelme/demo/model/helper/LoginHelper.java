package com.vriquelme.demo.model.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vriquelme.demo.model.dto.LoginDTO;
import com.vriquelme.demo.model.exception.EmailIsNullException;
import com.vriquelme.demo.model.exception.EmailNotFormatException;
import com.vriquelme.demo.model.exception.PasswordIsNullException;
import com.vriquelme.demo.model.services.LoginServices;
import com.vriquelme.demo.model.services.UserServices;
import com.vriquelme.demo.model.util.Constants;
import com.vriquelme.demo.model.util.ValidateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginHelper {

    private final static Logger logger = LogManager.getLogger(LoginHelper.class);
    private final LoginServices loginServices;
    private final UserServices userServices;
    private final ObjectMapper objectMapper;

    public LoginHelper(LoginServices p_loginServices, UserServices p_userServices, ObjectMapper p_objectMapper) {
        this.loginServices = p_loginServices;
        this.userServices = p_userServices;
        this.objectMapper = p_objectMapper;
    }

    public String getLogin(LoginDTO p_loginDTO){
        logger.info("==================== IN getLogin ======================");
        logger.info("Parameter[Email: " + p_loginDTO.getEmail() + ", Password: " + p_loginDTO.getPassword() + "]");
        if(ValidateUtils.isEmpty(p_loginDTO.getEmail())) {
            throw new EmailIsNullException(Constants.MESSAGE_ERROR_EMAIL_IS_NULL);
        } else if(!ValidateUtils.getValidEmail(p_loginDTO.getEmail())) {
            throw new EmailNotFormatException(Constants.MESSAGE_ERROR_FORMAT_EMAIL_INVALID);
        } else if (ValidateUtils.isEmpty(p_loginDTO.getPassword())) {
            throw new PasswordIsNullException(Constants.MESSAGE_ERROR_PASSWORD_IS_NULL);
        }

        try {
            return objectMapper.writeValueAsString(loginServices.getLoginUser(p_loginDTO));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.toString());
        }
    }


}
