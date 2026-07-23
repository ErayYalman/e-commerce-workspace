package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception;

import java.util.UUID;

public class UserNotFoundException extends BusinessException {
    
    public UserNotFoundException(UUID userId) {
        super(ErrorCode.USER_NOT_FOUND, "User not found with id: " + userId);
        //super ile, BusinessException sınıfının constructor'ını çağırıyoruz ve null değerini errorCode parametresi olarak veriyoruz.
        // Bu sayede, UserNotFoundException sınıfı oluşturulduğunda, BusinessException sınıfının constructor'ı çağrılır ve hata mesajı iletilir.
    }
    
}
