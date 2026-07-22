package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception;

import java.time.Instant;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> validationErrors; //validation hatalarını tutmak için bir Map ekledik. Bu Map, 
    // alan adlarını (field names) ve ilgili hata mesajlarını (error messages) saklayacak.
}
