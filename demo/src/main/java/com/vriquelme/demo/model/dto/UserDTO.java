package com.vriquelme.demo.model.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class UserDTO implements Serializable {

    private UUID id;
    private String name;
    private String email;
    private List<PhoneDTO> phonesList;
    private String phoneJson;
    private LocalDateTime dateCreate;
    private LocalDateTime dateModified;
    private LocalDateTime lastLogin;
    private Boolean isActive;
    private String token;
    private String password;

}
