package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.dto.AddressResponseDTO;
import com.SpringBootLearn.demo.entity.Address;
import com.SpringBootLearn.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository   extends JpaRepository<Address, Integer> {
    List<Address> findAllByUserId(Integer userId);
}
