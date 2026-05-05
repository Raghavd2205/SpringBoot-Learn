package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.common.ApiResponse;
import com.SpringBootLearn.demo.dto.*;
import com.SpringBootLearn.demo.service.OrderService;
import com.SpringBootLearn.demo.utility.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/addtocart")
    ResponseEntity<ApiResponse<OrderResponseDTO>> addToCart(@RequestBody CartRequestDTO cartBody){
        return ResponseUtil.success(this.orderService.addToCart(cartBody),201);
    }
    @DeleteMapping ("/removefromcart")
    ResponseEntity<ApiResponse<String>> removeFromCart(@RequestBody RemoveFromCartRequestDTO cartBody){
        return ResponseUtil.success(this.orderService.removeFromCart(cartBody),200);
    }
    @PostMapping
    ResponseEntity<ApiResponse<List<OrderResponseDTO>>> finalOrder(@RequestBody List<OrderRequestDTO> orderBody){
        return ResponseUtil.success(this.orderService.finalOrder(orderBody),201);
    }
}
