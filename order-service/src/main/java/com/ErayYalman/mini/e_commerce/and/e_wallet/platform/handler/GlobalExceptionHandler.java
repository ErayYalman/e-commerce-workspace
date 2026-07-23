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

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception.BusinessException;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice // @RestControllerAdvice, Spring Boot uygulamasında global exception handling (hata yönetimi) için kullanılan 
// bir anotasyondur. Bu anotasyon, uygulama genelinde meydana gelen istisnaları yakalamak ve uygun yanıtları döndürmek için kullanılır.
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BusinessException.class) // @ExceptionHandler, belirli bir istisna türünü yakalamak için kullanılır.
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex, HttpServletRequest request) {

        HttpStatus status = ex.getErrorCode().getHttpStatus(); // BusinessException sınıfının alt sınıflarından gelen istisnaların 
        // HTTP durum kodunu almak için kullanılır.
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .errorCode(ex.getErrorCode().name())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build(); //hepsini toplayıp ErrorResponse nesnesi oluşturuyoruz. Bu nesne, hata yanıtının içeriğini temsil eder ve
                // HTTP yanıtında döndürülecek bilgileri içerir.
        return ResponseEntity // ResponseEntity, HTTP yanıtını temsil eden bir sınıftır. Bu sınıf, yanıtın durum kodunu, 
        // başlıklarını ve gövdesini ayarlamak için kullanılır.
                .status(status)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
// MethodArgumentNotValidException, Spring Boot uygulamalarında, bir metodun parametrelerinin doğrulama kurallarına uymadığında 
// fırlatılan bir istisnadır. Bu istisna, genellikle @Valid veya @Validated anotasyonları ile işaretlenmiş metod parametrelerinde meydana gelir.
       
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
