package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dao.OrderRepository;
import com.SpringBootLearn.demo.dao.UserRepository;
import com.SpringBootLearn.demo.dao.ProductRepository;
import com.SpringBootLearn.demo.dto.CartRequestDTO;
import com.SpringBootLearn.demo.dto.OrderRequestDTO;
import com.SpringBootLearn.demo.dto.OrderResponseDTO;
import com.SpringBootLearn.demo.dto.RemoveFromCartRequestDTO;
import com.SpringBootLearn.demo.entity.Order;
import com.SpringBootLearn.demo.entity.Product;
import com.SpringBootLearn.demo.entity.User;
import com.SpringBootLearn.demo.enums.OrderStatus;
import com.SpringBootLearn.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.net.SocketOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public OrderResponseDTO addToCart(CartRequestDTO order) {
        Order duplicateOrder =  this.orderRepository.findDuplicates(order.getProductId(),order.getUserId(),OrderStatus.CART);

        if (duplicateOrder != null) {
            // exists → update quantity
            duplicateOrder.setQuantity(
                    duplicateOrder.getQuantity() + order.getQuantity()
            );
            Order order1 =  this.orderRepository.save(duplicateOrder);
            return toOrderDto(order1,"cart");
        }
        else{
            Order order1 = this.orderRepository.save(toEntityCart(order));
            return toOrderDto(order1,"cart");
        }

    }
    @Override
    public String removeFromCart(RemoveFromCartRequestDTO order){
        Order order1 = orderRepository
                .findByOrderIdAndUserIdAndStatus(
                        order.getOrderId(),
                        order.getUserId(),
                        OrderStatus.CART
                );

        if (order1 == null) {
            throw new ResourceNotFoundException("Order not found for this user");
        }
        if(order1.getQuantity()>1 && order1.getQuantity()>order.getQuantity()){
            order1.setQuantity(order1.getQuantity() - order.getQuantity());
            this.orderRepository.save(order1);
        }
        else{
            this.orderRepository.delete(order1);
        }
        return "Order with Id "+order.getOrderId()+" removed successfully ";
    }

    @Override
    public List<OrderResponseDTO> finalOrder(List<OrderRequestDTO> orders) {
        List<OrderResponseDTO> finalOrders = new ArrayList<>();
        Order finalOrder = new Order();
        for(OrderRequestDTO order : orders){
            System.out.println("order.getOrderId()"+order.getOrderId());
            if(order.getOrderId() == null){
                finalOrder = this.orderRepository.save(this.toEntityOrder(order));
                System.out.println("order.getOrderId() 2"+order.getOrderId());
            }
            else{
                Order order1 = this.orderRepository.findById(order.getOrderId()).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
                Product product = productRepository.findById(order.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
                User user = userRepository.findById(order.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
                order1.setProduct(product);
                order1.setUser(user);
                order1.setStatus(OrderStatus.ORDERED);
                order1.setQuantity(order.getQuantity());

                // Optional: set dateTime if not auto-handled
                order1.setDateTime(LocalDateTime.now());
                finalOrder = order1;
            }
            finalOrders.add(this.toOrderDto(finalOrder,"order"));
        }
        return finalOrders;
    }

    private Order toEntityCart(CartRequestDTO dto) {

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
    private Order toEntityOrder(OrderRequestDTO dto) {

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = new Order();

        order.setProduct(product);
        order.setUser(user);
        order.setStatus(OrderStatus.ORDERED);
        order.setQuantity(dto.getQuantity());

        // Optional: set dateTime if not auto-handled
        order.setDateTime(LocalDateTime.now());

        return order;
    }
    private OrderResponseDTO toOrderDto(Order order,String type) {

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getOrderId());
        dto.setProductId(order.getProduct().getId());
        dto.setProductName(order.getProduct().getName());
        dto.setCategory(order.getProduct().getCategory());
        dto.setPrice(order.getProduct().getPrice());
        dto.setUserId(order.getUser().getId());
        if(type == "cart"){
            dto.setStatus(OrderStatus.CART);
        }
        else if(type == "order"){
            dto.setStatus(OrderStatus.ORDERED);
        }
        dto.setQuantity(order.getQuantity());
        dto.setDateTime(order.getDateTime());

        return dto;
    }
}
