package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception;
import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {
//abstract class, bu sınıfın doğrudan örneklenemeyeceğini belirtir. Yani, BusinessException sınıfı doğrudan oluşturulamaz, 
// ancak alt sınıflar tarafından kullanılabilir.
    private final ErrorCode errorCode;

    protected BusinessException(ErrorCode errorCode, String message) { 
        //protected constructor, sadece bu sınıfın alt sınıfları tarafından erişilebilir. Bu sayede, B
        // usinessException sınıfı doğrudan oluşturulamaz, ancak alt sınıflar tarafından kullanılabilir.
        super(message);
        this.errorCode = errorCode;
        //super ile, RuntimeException sınıfının constructor'ını çağırıyoruz ve hata mesajını parametre olarak veriyoruz.

    }
    
}
