package com.SpringBootLearn.demo.dto;

import com.SpringBootLearn.demo.enums.OrderStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {

    private Integer productId;
    private Integer userId;
    private OrderStatus status;
    private Integer quantity;
}