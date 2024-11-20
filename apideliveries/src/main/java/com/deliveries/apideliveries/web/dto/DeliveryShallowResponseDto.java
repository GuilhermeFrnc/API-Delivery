package com.deliveries.apideliveries.web.dto;

import com.deliveries.apideliveries.enums.DeliveryStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryShallowResponseDto {
    private Long id;
    private String nameClient;
    private LocalDateTime createDateTime;
    private DeliveryStatus status;
}
