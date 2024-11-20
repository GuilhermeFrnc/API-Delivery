package com.deliveries.apideliveries.web.dto.mapper;

import com.deliveries.apideliveries.entity.Address;
import com.deliveries.apideliveries.entity.Delivery;
import com.deliveries.apideliveries.web.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class DeliveryMapper {

    public static Delivery toEntity (DeliveryRequestDto deliveryRequestDto){
        Delivery delivery = new Delivery();
        delivery.setNameClient(deliveryRequestDto.getNameClient());
        delivery.setNameStore(deliveryRequestDto.getNameStore());
        delivery.setAddress(toEntityAddress(deliveryRequestDto.getAddress()));

        return delivery;
    }

    public static Address toEntityAddress(AddressRequestDto addressRequestDto){
        Address address = new Address();

        address.setStreet(addressRequestDto.getStreet());
        address.setCity(addressRequestDto.getCity());
        address.setNumber(addressRequestDto.getNumber());
        address.setComplement(addressRequestDto.getComplement());
        address.setZipCode(addressRequestDto.getZipCode());

        return address;
    }

    public static DeliveryResponseDto toDto (Delivery delivery){
        DeliveryResponseDto deliveryResponseDto = new DeliveryResponseDto();

        deliveryResponseDto.setId(delivery.getId());
        deliveryResponseDto.setNameClient(delivery.getNameClient());
        deliveryResponseDto.setNameStore(delivery.getNameStore());
        deliveryResponseDto.setCreateDateTime(delivery.getCreateDateTime());
        deliveryResponseDto.setDeliveryDateTime(delivery.getDeliveryDateTime());
        deliveryResponseDto.setStatus(delivery.getStatus());
        deliveryResponseDto.setAddress(toDtoAddress(delivery.getAddress()));

        return deliveryResponseDto;
    }

    public static AddressResponseDto toDtoAddress(Address address){
        AddressResponseDto responseDto= new AddressResponseDto();

        responseDto.setCity(address.getCity());
        responseDto.setStreet(address.getStreet());
        responseDto.setNumber(address.getNumber());
        responseDto.setComplement(address.getComplement());
        responseDto.setZipCode(address.getZipCode());

        return responseDto;
    }

    public static DeliveryShallowResponseDto toDtoShallow(Delivery delivery) {
        DeliveryShallowResponseDto responseDto = new DeliveryShallowResponseDto();
        responseDto.setId(delivery.getId());
        responseDto.setNameClient(delivery.getNameClient());
        responseDto.setStatus(delivery.getStatus());
        responseDto.setCreateDateTime(delivery.getCreateDateTime());
        return responseDto;
    }

    public static List<DeliveryShallowResponseDto> toDtoShallowList(List<Delivery> deliveries) {
        return deliveries.stream()
                .map(DeliveryMapper::toDtoShallow)
                .collect(Collectors.toList());
    }
}
