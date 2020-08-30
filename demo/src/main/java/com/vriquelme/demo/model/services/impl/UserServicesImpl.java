package com.vriquelme.demo.model.services.impl;

import com.vriquelme.demo.model.dto.UserDTO;
import com.vriquelme.demo.model.entity.UserBO;
import com.vriquelme.demo.model.repository.UserRepository;
import com.vriquelme.demo.model.services.TokenAuthenticationServices;
import com.vriquelme.demo.model.services.UserServices;
import com.vriquelme.demo.model.util.UserUtil;
import com.vriquelme.demo.model.util.ValidateUtils;

import java.util.Optional;

public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;
    private final TokenAuthenticationServices tokenAuthenticationServices;

    public UserServicesImpl(UserRepository userRepository, TokenAuthenticationServices tokenAuthenticationServices) {
        this.userRepository = userRepository;
        this.tokenAuthenticationServices = tokenAuthenticationServices;
    }

    @Override
    public UserDTO getFindUserByEmail(UserDTO p_userDTO) {
        UserBO userBO = userRepository.findByEmail(p_userDTO.getEmail());
        if(ValidateUtils.isEmpty(userBO)) {
            return new UserDTO();
        } else {
            return UserUtil.convertBOtoDTO(userBO);
        }

    }

    @Override
    public UserDTO saveUser(UserDTO p_userDTO) {
        UserBO userBO = userRepository.findByEmail(p_userDTO.getEmail());

        if(ValidateUtils.isEmpty(userBO)) {
            userBO = UserUtil.convertDTOtoBO(p_userDTO);
        }
        userBO.setToken(tokenAuthenticationServices.authorize(userBO.getEmail()));

        return UserUtil.convertBOtoDTO(userRepository.save(userBO));
    }
}
