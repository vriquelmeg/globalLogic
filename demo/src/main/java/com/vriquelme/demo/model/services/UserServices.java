package com.vriquelme.demo.model.services;

import com.vriquelme.demo.model.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {
    public UserDTO getFindUserByEmail(UserDTO p_userDTO);
    public UserDTO saveUser(UserDTO p_userDTO);
}
