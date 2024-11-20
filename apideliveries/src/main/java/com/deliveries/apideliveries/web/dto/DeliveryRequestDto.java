package com.deliveries.apideliveries.web.dto;

import lombok.Data;

@Data
public class DeliveryRequestDto {
    private String nameClient;
    private String nameStore;
    private AddressRequestDto address;
}
