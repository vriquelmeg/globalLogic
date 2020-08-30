package com.vriquelme.demo.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class LoginDTO implements Serializable {

    private String email;
    private String password;

}
