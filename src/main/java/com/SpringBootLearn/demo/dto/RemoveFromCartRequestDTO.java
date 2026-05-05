package com.SpringBootLearn.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemoveFromCartRequestDTO {

    private Integer orderId;
    private Integer userId;
    private Integer quantity; // how much to remove
}