package com.deliveries.apideliveries.web.controller;

import com.deliveries.apideliveries.entity.Delivery;
import com.deliveries.apideliveries.service.DeliveryService;
import com.deliveries.apideliveries.web.dto.DeliveryRequestDto;
import com.deliveries.apideliveries.web.dto.DeliveryResponseDto;
import com.deliveries.apideliveries.web.dto.DeliveryShallowResponseDto;
import com.deliveries.apideliveries.web.dto.mapper.DeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<DeliveryResponseDto> createDelivery(@RequestBody DeliveryRequestDto deliveryRequestDto) {
        Delivery delivery = DeliveryMapper.toEntity(deliveryRequestDto);
        Delivery saveDelivery = deliveryService.createDelivery(delivery);
        DeliveryResponseDto deliveryResponseDto = DeliveryMapper.toDto(saveDelivery);
        return ResponseEntity.ok(deliveryResponseDto);
    }

    @GetMapping("/last-week")
    public ResponseEntity<List<DeliveryShallowResponseDto>> getDeliveriesFromLastWeek() {
        List<Delivery> deliveries = deliveryService.getDeliveries();

        if (deliveries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<DeliveryShallowResponseDto> responseDtos = DeliveryMapper.toDtoShallowList(deliveries);
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<DeliveryShallowResponseDto>> getPendingDeliveries() {
        List<Delivery> pendingDeliveries = deliveryService.getPendingDeliveries();
        if (pendingDeliveries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<DeliveryShallowResponseDto> responseDtos = DeliveryMapper.toDtoShallowList(pendingDeliveries);
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<DeliveryShallowResponseDto>> getPendingDeliveriesFromLastWeek() {
        List<Delivery> deliveries = deliveryService.getDeliveriesFromLastWeek();
        if (deliveries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<DeliveryShallowResponseDto> responseDtos = DeliveryMapper.toDtoShallowList(deliveries);
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDto> findDelivery(@PathVariable Long id) {
        try {
            Delivery findDelivery = deliveryService.findDelivery(id);
            DeliveryResponseDto deliveryResponseDto = DeliveryMapper.toDto(findDelivery);
            return ResponseEntity.ok(deliveryResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/update-status/{id}")
    public ResponseEntity<DeliveryResponseDto> markAsDelivered(@PathVariable Long id) {
        try {
            Delivery updatedDelivery = deliveryService.updateDeliveryStatusToDelivered(id);
            DeliveryResponseDto deliveryResponseDto = DeliveryMapper.toDto(updatedDelivery);
            return ResponseEntity.ok(deliveryResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        try {
            deliveryService.deleteDelivery(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
