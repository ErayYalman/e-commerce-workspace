package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception;

public class EmailAlreadyExistsException extends BusinessException {
    
    public EmailAlreadyExistsException(String email) {
        super(ErrorCode.EMAIL_ALREADY_EXISTS, "Email already exists: " + email);
        //super ile, BusinessException sınıfının constructor'ını çağırıyoruz ve null değerini errorCode parametresi olarak veriyoruz.
        // Bu sayede, EmailAlreadyExistsException sınıfı oluşturulduğunda, BusinessException sınıfının constructor'ı çağrılır ve hata mesajı iletilir.
    }

}
