package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dao.OrderRepository;
import com.SpringBootLearn.demo.dao.UserRepository;
import com.SpringBootLearn.demo.dao.ProductRepository;
import com.SpringBootLearn.demo.dto.OrderRequestDTO;
import com.SpringBootLearn.demo.dto.OrderResponseDTO;
import com.SpringBootLearn.demo.entity.Order;
import com.SpringBootLearn.demo.entity.Product;
import com.SpringBootLearn.demo.entity.User;
import com.SpringBootLearn.demo.enums.OrderStatus;
import com.SpringBootLearn.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class OrderServiceImpl implements OrderService{
    OrderRepository orderRepository;
    ProductRepository productRepository;
    UserRepository userRepository;


    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderResponseDTO addToCart(OrderRequestDTO order) {
        Order duplicateOrder =  this.orderRepository.findDuplicates(order.getProductId(),order.getUserId(),OrderStatus.CART);

        if (duplicateOrder != null) {
            // exists → update quantity
            duplicateOrder.setQuantity(
                    duplicateOrder.getQuantity() + order.getQuantity()
            );
            Order order1 =  this.orderRepository.save(duplicateOrder);
            return toOrderDto(order1);
        }
        else{
            Order order1 = this.orderRepository.save(toEntity(order));
            return toOrderDto(order1);
        }

    }
    private Order toEntity(OrderRequestDTO dto) {

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = new Order();

        order.setProduct(product);
        order.setUser(user);
        order.setStatus(OrderStatus.CART);
        order.setQuantity(dto.getQuantity());

        // Optional: set dateTime if not auto-handled
        order.setDateTime(LocalDateTime.now());

        return order;
    }
    private OrderResponseDTO toOrderDto(Order order) {

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getOrderId());
        dto.setProductId(order.getProduct().getId());
        dto.setProductName(order.getProduct().getName());
        dto.setCategory(order.getProduct().getCategory());
        dto.setPrice(order.getProduct().getPrice());
        dto.setUserId(order.getUser().getId());
        dto.setStatus(OrderStatus.CART);
        dto.setQuantity(order.getQuantity());
        dto.setDateTime(order.getDateTime());

        return dto;
    }
}
