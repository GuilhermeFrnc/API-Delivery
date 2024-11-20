package com.deliveries.apideliveries.web.dto;

import lombok.Data;

@Data
public class AddressResponseDto {
    private String street;
    private String city;
    private String number;
    private String complement;
    private String zipCode;
}
