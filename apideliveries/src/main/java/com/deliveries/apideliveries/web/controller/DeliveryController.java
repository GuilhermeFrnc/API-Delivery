package com.deliveries.apideliveries.web.controller;

import com.deliveries.apideliveries.entity.Address;
import com.deliveries.apideliveries.entity.Delivery;
import com.deliveries.apideliveries.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        Delivery createdDelivery = deliveryService.createDelivery(delivery);
        return ResponseEntity.ok(createdDelivery);
    }

    @GetMapping("/last-week")
    public ResponseEntity<List<Delivery>> getDeliveriesFromLastWeek() {
        List<Delivery> deliveries = deliveryService.getDeliveries();
        if (deliveries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(deliveries);
    }
}
