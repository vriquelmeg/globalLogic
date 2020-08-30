package com.vriquelme.demo.model.util;

import com.vriquelme.demo.model.dto.UserDTO;
import com.vriquelme.demo.model.entity.UserBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserUtil {

    private final static Logger logger = LoggerFactory.getLogger(UserUtil.class);

    public static UserBO convertDTOtoBO(UserDTO p_userDTO) {
        logger.info("=============== IN UserUtil.convertDTOtoBO ===================");
        UserBO userBO = UserBO.builder()
                .id(p_userDTO.getId())
                .phones(p_userDTO.getPhoneJson())
                .name(p_userDTO.getName())
                .email(p_userDTO.getEmail())
                .password(p_userDTO.getPassword())
                .dateCreate(Optional.ofNullable(p_userDTO.getDateCreate()).orElse(LocalDateTime.now()))
                .dateModified(Optional.ofNullable(p_userDTO.getDateModified()).orElse(LocalDateTime.now()))
                .lastLogin(Optional.ofNullable(p_userDTO.getLastLogin()).orElse(LocalDateTime.now()))
                .isActive(true)
                .token(p_userDTO.getToken())
                .build();

        logger.info("Object userBO : [" + userBO.toString() + "]");
        return userBO;
    }

    public static UserDTO convertBOtoDTO(UserBO p_userBO) {
        logger.info("=============== IN UserUtil.convertDTOtoBO ===================");

        return UserDTO.builder()
                .id(p_userBO.getId())
                .name(p_userBO.getName())
                .email(p_userBO.getEmail())
                .password(p_userBO.getPassword())
                .phoneJson(p_userBO.getPhones())
                .dateCreate(Optional.ofNullable(p_userBO.getDateCreate()).orElse(LocalDateTime.now()))
                .dateModified(Optional.ofNullable(p_userBO.getDateModified()).orElse(LocalDateTime.now()))
                .lastLogin(Optional.ofNullable(p_userBO.getLastLogin()).orElse(LocalDateTime.now()))
                .isActive(true)
                .token(p_userBO.getToken())
                .build();
    }
}
