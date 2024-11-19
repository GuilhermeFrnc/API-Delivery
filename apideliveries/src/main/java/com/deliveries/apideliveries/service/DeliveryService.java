package com.deliveries.apideliveries.service;

import com.deliveries.apideliveries.entity.Address;
import com.deliveries.apideliveries.entity.Delivery;
import com.deliveries.apideliveries.enums.DeliveryStatus;
import com.deliveries.apideliveries.repository.AddressRepository;
import com.deliveries.apideliveries.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
