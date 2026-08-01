package com.example.ordersservice.service;

import com.example.ordersservice.*;
import com.example.ordersservice.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService
{
   private final OrderRepository orderRepository;
   private final InventoryClient inventoryClient;

   public Order createOrder(OrderRequest orderRequest)
   {
       InventoryRespone p = inventoryClient.getProduct(orderRequest.getProductId());

       if (p.getQuantity() < orderRequest.getQuantity())
       {
           throw new RuntimeException("Not enough stock");
       }

       Order o = new Order();

       o.setProductId(orderRequest.getProductId());
       o.setQuantity(orderRequest.getQuantity());
       o.setStatus("COMPLETED");
       o.setTotalPrice(p.getPrice().multiply(BigDecimal.valueOf(orderRequest.getQuantity())));

       return orderRepository.save(o);

   }




}
