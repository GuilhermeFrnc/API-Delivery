package com.deliveries.apideliveries.service;

import com.deliveries.apideliveries.entity.Address;
import com.deliveries.apideliveries.entity.Delivery;
import com.deliveries.apideliveries.enums.DeliveryStatus;
import com.deliveries.apideliveries.repository.AddressRepository;
import com.deliveries.apideliveries.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Delivery createDelivery(Delivery delivery) {
        if (delivery.getAddress() == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        if (delivery.getNameClient() == null || delivery.getNameClient().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be empty");
        }

        Optional<Address> existingAddress = addressRepository.findByStreetAndCityAndNumberAndZipCode(
                delivery.getAddress().getStreet(),
                delivery.getAddress().getCity(),
                delivery.getAddress().getNumber(),
                delivery.getAddress().getZipCode()
        );

        if (existingAddress.isPresent()) {
            delivery.setAddress(existingAddress.get());
        } else {
            Address newAddress = addressRepository.save(delivery.getAddress());
            delivery.setAddress(newAddress);
        }

        delivery.setStatus(DeliveryStatus.PENDING);

        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getDeliveries() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(7);

        return deliveryRepository.findAllByCreateDateTimeBetween(startDate, endDate);
    }

    public List<Delivery> getPendingDeliveries() {
        return deliveryRepository.findAllByStatus(DeliveryStatus.PENDING);
    }

    public List<Delivery> getDeliveriesFromLastWeek() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(7);

        return deliveryRepository.findAllByCreateDateTimeBetweenAndStatus(
                startDate,
                endDate,
                DeliveryStatus.DELIVERED
        );
    }

    public Delivery updateDeliveryStatusToDelivered(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Delivery not found with ID: " + deliveryId));

        if (delivery.getStatus() != DeliveryStatus.PENDING) {
            throw new IllegalStateException("Only deliveries with status PENDING can be updated to DELIVERED");
        }

        delivery.setStatus(DeliveryStatus.DELIVERED);

        return deliveryRepository.save(delivery);
    }


    public void deleteDelivery(Long deliveryId) {
        if (!deliveryRepository.existsById(deliveryId)) {
            throw new IllegalArgumentException("Delivery not found with ID: " + deliveryId);
        }
        deliveryRepository.deleteById(deliveryId);
    }

}
