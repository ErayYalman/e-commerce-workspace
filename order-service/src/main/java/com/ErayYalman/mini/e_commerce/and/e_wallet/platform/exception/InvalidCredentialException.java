package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception;

public class InvalidCredentialException extends BusinessException {
    public InvalidCredentialException() {
       super(ErrorCode.INVALID_CREDENTIALS, "Invalid email or password");
    }
    
}
