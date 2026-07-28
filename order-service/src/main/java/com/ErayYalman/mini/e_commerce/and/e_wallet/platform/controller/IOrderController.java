package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller;

import java.util.List;
import java.util.UUID;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.OrderRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.OrderResponse;

public interface IOrderController {
    
    OrderResponse CreateOrder(UUID userId, OrderRequest orderRequest);

    OrderResponse getOrderById(UUID orderId);

     List<OrderResponse> getOrdersByUserId(UUID userId);

}
