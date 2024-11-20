package com.deliveries.apideliveries.web.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AddressRequestDto {

    private String street;
    private String city;
    private String number;
    private String complement;
    private String zipCode;
}
