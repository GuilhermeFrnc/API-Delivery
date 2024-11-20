package com.deliveries.apideliveries.web.dto;

import com.deliveries.apideliveries.enums.DeliveryStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryResponseDto {

    private Long id;
    private String nameClient;
    private String nameStore;
    private AddressResponseDto address;
    private LocalDateTime createDateTime;
    private LocalDateTime deliveryDateTime;
    private DeliveryStatus status;
}
