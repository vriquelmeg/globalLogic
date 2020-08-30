package com.vriquelme.demo.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ErrorDTO implements Serializable {

    private String errorId;
    private String message;
}
