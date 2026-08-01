package com.example.ordersservice;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
public class InventoryRespone
{
    private Long id;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
