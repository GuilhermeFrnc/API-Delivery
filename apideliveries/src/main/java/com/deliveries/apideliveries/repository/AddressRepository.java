package com.deliveries.apideliveries.repository;

import com.deliveries.apideliveries.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByStreetAndCityAndNumberAndZipCode(String street, String city, String number, String zipCode);
}
