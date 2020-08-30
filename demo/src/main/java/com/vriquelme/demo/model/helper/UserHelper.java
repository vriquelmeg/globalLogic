package com.vriquelme.demo.model.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vriquelme.demo.model.dto.UserDTO;
import com.vriquelme.demo.model.exception.*;
import com.vriquelme.demo.model.services.UserServices;
import com.vriquelme.demo.model.util.Constants;
import com.vriquelme.demo.model.util.ValidateUtils;

public class UserHelper {

    private final UserServices userServices;
    private final ObjectMapper objectMapper;

    public UserHelper (UserServices userServices, ObjectMapper objectMapper) {
        this.userServices = userServices;
        this.objectMapper = objectMapper;
    }

    public String createUser(UserDTO p_userDTO) {
        if(ValidateUtils.isEmpty(p_userDTO.getEmail())) {
            throw new EmailIsNullException(Constants.MESSAGE_ERROR_EMAIL_IS_NULL);
        } else if (ValidateUtils.isEmpty(p_userDTO.getPassword())) {
            throw new PasswordIsNullException(Constants.MESSAGE_ERROR_PASSWORD_IS_NULL);
        }else if (!ValidateUtils.getValidEmail(p_userDTO.getEmail())) {
            throw new EmailNotFormatException(Constants.MESSAGE_ERROR_FORMAT_EMAIL_INVALID);
        } else if (!ValidateUtils.validFormatPassword(p_userDTO.getPassword())) {
            throw new PasswordNotCorrectFormatException(Constants.MESSAGE_ERROR_NOT_CORRECT_FORMAT_PASSWORD);
        }

        UserDTO userDTO = userServices.getFindUserByEmail(p_userDTO);

        if(ValidateUtils.isEmpty(userDTO.getEmail())) {
            p_userDTO.setId(ValidateUtils.generateId());
            userDTO = userServices.saveUser(p_userDTO);
        } else {
            throw new UserRegisterException(Constants.MESSAGE_ERROR_USER_REGISTER);
        }
        try {
            return objectMapper.writeValueAsString(userDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public String updateUser(UserDTO p_userDTO){
        UserDTO userDTOSave = userServices.saveUser(p_userDTO);
        if(!ValidateUtils.isEmpty(userDTOSave)) {
            try {
                return objectMapper.writeValueAsString(userDTOSave);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e.toString());
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }

        return Constants.MESSAGE_NOT_UPDATE_USER;
    }


}
