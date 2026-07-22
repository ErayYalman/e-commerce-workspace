package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.handler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception.ErrorResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception.ProductNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice // @RestControllerAdvice, Spring Boot uygulamasında global exception handling (hata yönetimi) için kullanılan 
// bir anotasyondur. Bu anotasyon, uygulama genelinde meydana gelen istisnaları yakalamak ve uygun yanıtları döndürmek için kullanılır.
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ProductNotFoundException.class) // @ExceptionHandler, belirli bir istisna türünü yakalamak için kullanılır.
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
       
        Map<String, String> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, replacement) -> existing, // Eğer aynı alan için birden fazla hata varsa, mevcut değeri koru
                        LinkedHashMap::new //ekleme sırası korunması için
                ));

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed")
                .path(request.getRequestURI())
                .validationErrors(validationErrors)
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

}
