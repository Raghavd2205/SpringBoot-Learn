package com.SpringBootLearn.demo.dto;

import com.SpringBootLearn.demo.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {

    private Integer orderId;
    private Integer productId;
    private String productName;
    private String category;
    private Integer price;

    private Integer userId;
    private OrderStatus status;
    private Integer quantity;

    private LocalDateTime dateTime;
}