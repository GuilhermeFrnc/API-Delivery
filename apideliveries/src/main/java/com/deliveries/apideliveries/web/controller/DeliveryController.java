package com.deliveries.apideliveries.web.controller;

import com.deliveries.apideliveries.entity.Delivery;
import com.deliveries.apideliveries.service.DeliveryService;
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

    @GetMapping("/pending")
    public ResponseEntity<List<Delivery>> getPendingDeliveries() {
        List<Delivery> pendingDeliveries = deliveryService.getPendingDeliveries();
        if (pendingDeliveries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pendingDeliveries);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Delivery>> getPendingDeliveriesFromLastWeek() {
        List<Delivery> deliveries = deliveryService.getDeliveriesFromLastWeek();
        if (deliveries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(deliveries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> findDelivery(@PathVariable Long id) {
        try {
            Delivery findDelivery = deliveryService.findDelivery(id);
            return ResponseEntity.ok(findDelivery);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/update-status/{id}")
    public ResponseEntity<Delivery> markAsDelivered(@PathVariable Long id) {
        try {
            Delivery updatedDelivery = deliveryService.updateDeliveryStatusToDelivered(id);
            return ResponseEntity.ok(updatedDelivery);
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
