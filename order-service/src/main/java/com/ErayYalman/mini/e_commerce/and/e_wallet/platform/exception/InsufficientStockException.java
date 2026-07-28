package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception;

import java.util.UUID;

public class InsufficientStockException extends BusinessException {
    public InsufficientStockException(UUID productId) {
        super(ErrorCode.INSUFFICIENT_STOCK, "Insufficient stock for the requested product."+productId);
    }
    
}
