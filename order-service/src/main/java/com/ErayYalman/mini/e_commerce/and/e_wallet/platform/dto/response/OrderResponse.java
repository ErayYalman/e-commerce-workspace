package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {

    private UUID id;

    private BigDecimal totalPrice;

    private OrderStatus status;

    private Instant createdAt;

    private List<OrderItemResponse> orderItems;

    

}
