package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
    
    private UUID productId;

    private String productName;

    private int quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;



}
