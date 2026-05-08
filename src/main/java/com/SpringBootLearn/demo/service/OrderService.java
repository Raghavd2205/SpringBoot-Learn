package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dto.*;

import java.util.List;


public interface OrderService {
    OrderResponseDTO addToCart(CartRequestDTO order);
    String removeFromCart(RemoveFromCartRequestDTO order);

    List<OrderResponseDTO> finalOrder(List<OrderRequestDTO> orders);

    AddressResponseDTO addAddress(AddressRequestDTO address);
    List<AddressResponseDTO> listAllAddress(Integer userId);
    AddressResponseDTO updateAddress(AddressRequestDTO address);
    String deleteAddress(AddressRequestDTO address);
}
