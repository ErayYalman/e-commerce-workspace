package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(UUID productId) {
        super("Product not found with ID: " + productId);
        //super ile, RuntimeException sınıfının constructor'ını çağırıyoruz ve hata mesajını parametre olarak veriyoruz. 
        // Bu sayede, ProductNotFoundException oluşturulduğunda, ilgili ürünün bulunamadığını belirten bir mesaj iletilir.
    }
}