package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.Order;
import com.SpringBootLearn.demo.entity.Product;
import com.SpringBootLearn.demo.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Integer> {
    @Query("""
    SELECT o FROM Order o
    WHERE o.user.id = :userId 
      AND o.product.id = :productId 
      AND o.status = :status
""")
    Order findDuplicates(
            @Param("productId") Integer productId,
            @Param("userId") Integer userId,
            @Param("status") OrderStatus status
    );
    Order findByOrderIdAndUserIdAndStatus(Integer orderId, Integer userId,OrderStatus status);
}
