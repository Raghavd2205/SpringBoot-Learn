package com.SpringBootLearn.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    // Many orders can have same product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="street1")
    private String street1;

    @Column(name="street2")
    private String street2;

    @Column(name="landmark")
    private String landmark;

    @Column(name="pin_code")
    private Long pinCode;

    @Column(name="city")
    private String city;

    @Column(name="state_name")
    private String state;

    @Column(name="country")
    private String country;

    @Column(name="primary_address")
    private boolean primary;

}
