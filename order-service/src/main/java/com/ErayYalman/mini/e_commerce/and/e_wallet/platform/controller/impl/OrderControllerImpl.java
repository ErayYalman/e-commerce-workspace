package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller.IOrderController;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.OrderRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.OrderResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.IOrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderControllerImpl implements IOrderController {
    private final IOrderService orderService;


    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse CreateOrder(@RequestParam UUID userId, @Valid @RequestBody OrderRequest orderRequest) {
       return orderService.createOrder(userId, orderRequest);
    }

    @Override
    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderById(@PathVariable UUID orderId) {
      return orderService.getOrderById(orderId);
    }

    @Override
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrdersByUserId(@PathVariable UUID userId) {
      return orderService.getOrdersByUserId(userId);  
    }
   
    
}
