package com.vriquelme.demo.model.services.impl;

import com.vriquelme.demo.model.dto.LoginDTO;
import com.vriquelme.demo.model.dto.UserDTO;
import com.vriquelme.demo.model.entity.UserBO;
import com.vriquelme.demo.model.exception.PasswordNotMachedException;
import com.vriquelme.demo.model.exception.UserNotFoundException;
import com.vriquelme.demo.model.repository.UserRepository;
import com.vriquelme.demo.model.services.LoginServices;
import com.vriquelme.demo.model.services.TokenAuthenticationServices;
import com.vriquelme.demo.model.util.Constants;
import com.vriquelme.demo.model.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class LoginServicesImpl implements LoginServices {

    private static final Logger logger = LoggerFactory.getLogger(LoginServicesImpl.class);
    private final UserRepository userRepository;
    private final TokenAuthenticationServices tokenAuthenticationServices;

    public LoginServicesImpl(UserRepository userRepository, TokenAuthenticationServices tokenAuthenticationServices) {
        this.userRepository = userRepository;
        this.tokenAuthenticationServices = tokenAuthenticationServices;
    }

    @Override
    public UserDTO getLoginUser(LoginDTO p_loginDTO)  {
        logger.info("=============== IN getLoginUser ================");
        logger.info(p_loginDTO.toString());
        UserBO userBO = userRepository.findByEmail(p_loginDTO.getEmail());
        if(Objects.isNull(userBO)) {
            throw new UserNotFoundException(Constants.MESSAGE_ERROR_USER_NOT_FOUND);
        } else {
            if (!userBO.getPassword().equals(p_loginDTO.getPassword())) {
                throw new PasswordNotMachedException(Constants.MESSAGE_ERROR_PASSWORD_NOT_MATCH);
            } else {
                return UserUtil.convertBOtoDTO(userBO);
            }
        }
    }


}
