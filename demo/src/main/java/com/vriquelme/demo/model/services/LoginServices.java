package com.vriquelme.demo.model.services;

import com.vriquelme.demo.model.dto.LoginDTO;
import com.vriquelme.demo.model.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface LoginServices {

    public UserDTO getLoginUser(LoginDTO p_loginDTO);
}
