package com.example.ordersservice.controller;

import com.example.ordersservice.OrderRequest;
import com.example.ordersservice.entity.Order;
import com.example.ordersservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;

    @PostMapping
    public Order create(@RequestBody OrderRequest request)
    {
        return orderService.createOrder(request);
    }

}
