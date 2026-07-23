package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception;

import java.util.UUID;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(UUID productId) {
        super(ErrorCode.PRODUCT_NOT_FOUND,"product not found with id: " + productId);
        //super ile, BusinessException sınıfının constructor'ını çağırıyoruz ve null değerini errorCode parametresi olarak veriyoruz.
        // Bu sayede, ProductNotFoundException sınıfı oluşturulduğunda, BusinessException sınıfının constructor'ı çağrılır ve hata mesajı iletilir.
    }

}