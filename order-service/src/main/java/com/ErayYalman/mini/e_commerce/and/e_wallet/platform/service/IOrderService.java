package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service;

import java.util.List;
import java.util.UUID;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.OrderRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.OrderResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.security.CustomUserDetails;

public interface IOrderService {
    
    OrderResponse createOrder(CustomUserDetails userDetails, OrderRequest orderRequest);

    OrderResponse getOrderById(UUID orderId);   

    List<OrderResponse> getOrdersByUserId(CustomUserDetails userDetails);

}
