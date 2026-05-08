package com.SpringBootLearn.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {
    private int id;
    private Long userId;
    private String street1;
    private String street2;
    private String landmark;
    private Long pinCode;
    private String city;
    private String state;
    private String country;
    private boolean primary;
}
