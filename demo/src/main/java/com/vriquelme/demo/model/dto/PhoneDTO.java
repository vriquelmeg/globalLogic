package com.vriquelme.demo.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class PhoneDTO {

    private Integer phone;
    private Integer countryCode;
    private Integer cityCode;

}
