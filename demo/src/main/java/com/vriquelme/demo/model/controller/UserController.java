package com.vriquelme.demo.model.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vriquelme.demo.model.dto.ErrorDTO;
import com.vriquelme.demo.model.dto.UserDTO;
import com.vriquelme.demo.model.exception.EmailNotFormatException;
import com.vriquelme.demo.model.exception.PasswordNotCorrectFormatException;
import com.vriquelme.demo.model.exception.UserRegisterException;
import com.vriquelme.demo.model.helper.UserHelper;
import com.vriquelme.demo.model.util.Constants;
import com.vriquelme.demo.model.util.ErrorUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserHelper userHelper;
    private ObjectMapper objectMapper;

    @RequestMapping(value="/save", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String save(@RequestBody UserDTO userDTO){
        logger.info("Tamaño List: " + userDTO.getPhonesList());
        String jsonPhone = new Gson().toJson(userDTO.getPhonesList());
        userDTO.setPhoneJson(jsonPhone);
        logger.info("jsonPhone: " + jsonPhone);
        String response = "";

        try {
            response = userHelper.createUser(userDTO);
        } catch (EmailNotFormatException emailNotFormatException) {
            response = ErrorUtils.getError(Constants.CODE_ERROR_FORMAT_EMAIL_INVALID.toString(),emailNotFormatException.toString())
        } catch (PasswordNotCorrectFormatException ex) {
            response = ErrorUtils.getError(Constants.CODE_ERROR_INCORRECT_FORMAT_PASSWORD.toString(), ex.toString());
        } catch (UserRegisterException userRegisterException) {
            response = ErrorUtils.getError(Constants.CODE_ERROR_USER_REGISTER.toString(),userRegisterException.toString());
        }
        return response;
    }

    @PutMapping
    public String updateUser(@RequestBody UserDTO user){
        logger.info("UserDTO {", user.toString());
        return userHelper.updateUser(user);
    }
}
