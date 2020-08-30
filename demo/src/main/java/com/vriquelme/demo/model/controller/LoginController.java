package com.vriquelme.demo.model.controller;

import com.vriquelme.demo.model.dto.LoginDTO;
import com.vriquelme.demo.model.exception.EmailIsNullException;
import com.vriquelme.demo.model.exception.EmailNotFormatException;
import com.vriquelme.demo.model.exception.PasswordIsNullException;
import com.vriquelme.demo.model.exception.UserNotFoundException;
import com.vriquelme.demo.model.helper.LoginHelper;
import com.vriquelme.demo.model.util.Constants;
import com.vriquelme.demo.model.util.ErrorUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final LoginHelper loginHelper;

    @PostMapping("/auth")
    @ResponseBody
    public String login(@RequestBody LoginDTO loginDTO){
        String response = "";
        try {
            response = loginHelper.getLogin(loginDTO);
        } catch (EmailIsNullException emailIsNullException) {
            response = ErrorUtils.getError(Constants.CODE_ERROR_EMAIL_NULL.toString(),Constants.MESSAGE_ERROR_EMAIL_IS_NULL);
        } catch (EmailNotFormatException emailNotFormatException) {
            response = ErrorUtils.getError(Constants.CODE_ERROR_FORMAT_EMAIL_INVALID.toString(), Constants.MESSAGE_ERROR_FORMAT_EMAIL_INVALID);
        } catch (PasswordIsNullException passwordIsNullException) {
            response = ErrorUtils.getError(Constants.CODE_ERROR_PASSWORD_IS_NULL.toString(), Constants.MESSAGE_ERROR_PASSWORD_IS_NULL);
        } catch (UserNotFoundException userNotFoundException) {
            response = ErrorUtils.getError(Constants.CODE_ERROR_USER_NOT_FOUND.toString(), Constants.MESSAGE_ERROR_USER_NOT_FOUND);
        }
        return response;
    }
}
