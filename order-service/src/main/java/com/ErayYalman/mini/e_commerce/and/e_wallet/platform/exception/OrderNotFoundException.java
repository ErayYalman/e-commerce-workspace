package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception;

import java.util.UUID;

public class OrderNotFoundException extends BusinessException {
    
   public OrderNotFoundException(UUID orderId) {
        super(ErrorCode.ORDER_NOT_FOUND,"Order not found with ID " + orderId);

    }

}
