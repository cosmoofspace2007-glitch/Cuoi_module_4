package com.example.ordersservice;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Table
@Entity
public class Product
{
    @Id
    private Long id;
    private Long product_name;
    private Integer quantity;
    private BigDecimal price;


}
