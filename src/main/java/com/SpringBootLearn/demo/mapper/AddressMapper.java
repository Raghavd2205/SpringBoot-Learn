package com.SpringBootLearn.demo.mapper;

import com.SpringBootLearn.demo.dto.AddressRequestDTO;
import com.SpringBootLearn.demo.dto.AddressResponseDTO;
import com.SpringBootLearn.demo.entity.Address;
import com.SpringBootLearn.demo.entity.User;

public class AddressMapper {

    public static Address toEntity(AddressRequestDTO dto, User user) {
        Address address = new Address();
        address.setUser(user);
        address.setStreet1(dto.getStreet1());
        address.setStreet2(dto.getStreet2());
        address.setLandmark(dto.getLandmark());
        address.setPinCode(dto.getPinCode());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCountry(dto.getCountry());
        address.setPrimary(dto.isPrimary());
        return address;
    }

    public static AddressResponseDTO toResponseDTO(Address address) {
        return new AddressResponseDTO(
                address.getId(),
                (long) address.getUser().getId(),
                address.getStreet1(),
                address.getStreet2(),
                address.getLandmark(),
                address.getPinCode(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.isPrimary()
        );
    }
}
