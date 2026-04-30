package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dto.OrderRequestDTO;
import com.SpringBootLearn.demo.dto.OrderResponseDTO;



public interface OrderService {
    OrderResponseDTO addToCart(OrderRequestDTO order);
}
