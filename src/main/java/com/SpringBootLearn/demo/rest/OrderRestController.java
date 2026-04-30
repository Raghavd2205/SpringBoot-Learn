package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.common.ApiResponse;
import com.SpringBootLearn.demo.dto.ExpenseTrackerRequestDTO;
import com.SpringBootLearn.demo.dto.ExpenseTrackerResponseDTO;
import com.SpringBootLearn.demo.dto.OrderRequestDTO;
import com.SpringBootLearn.demo.dto.OrderResponseDTO;
import com.SpringBootLearn.demo.service.OrderService;
import com.SpringBootLearn.demo.utility.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/addtocart")
    ResponseEntity<ApiResponse<OrderResponseDTO>> addToCart(@RequestBody OrderRequestDTO cartBody){
        return ResponseUtil.success(this.orderService.addToCart(cartBody),201);
    }
}
