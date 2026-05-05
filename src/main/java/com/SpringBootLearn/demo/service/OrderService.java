package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dto.CartRequestDTO;
import com.SpringBootLearn.demo.dto.OrderRequestDTO;
import com.SpringBootLearn.demo.dto.OrderResponseDTO;
import com.SpringBootLearn.demo.dto.RemoveFromCartRequestDTO;

import java.util.List;


public interface OrderService {
    OrderResponseDTO addToCart(CartRequestDTO order);
    String removeFromCart(RemoveFromCartRequestDTO order);

    List<OrderResponseDTO> finalOrder(List<OrderRequestDTO> orders);
}
