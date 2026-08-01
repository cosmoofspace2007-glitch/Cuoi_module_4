package com.example.ordersservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "InventoryService")
public interface InventoryClient
{
    @GetMapping("/api/inventory/{productId}")
    InventoryRespone getProduct(@PathVariable Long productId);

}
