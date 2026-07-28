package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND),

    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND),

    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT),

    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED),

    INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST),

    INSUFFICIENT_STOCK(HttpStatus.CONFLICT),

    INVALID_PAYMENT_METHOD(HttpStatus.BAD_REQUEST),

    PAYMENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR),

    INVALID_ORDER_STATUS(HttpStatus.BAD_REQUEST),
    
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED);


    private final HttpStatus httpStatus; 

}
