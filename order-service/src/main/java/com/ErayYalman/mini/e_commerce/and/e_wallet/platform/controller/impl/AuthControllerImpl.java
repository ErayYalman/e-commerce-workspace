package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller.IAuthcontroller;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.LoginRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.RegisterRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.LoginResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.UserResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.IAuthService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements IAuthcontroller {
    private final IAuthService authService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
        //ResponseEntity sınıfı, HTTP yanıtlarını temsil eden bir sınıftır içinde HTTP durum kodu, başlıklar ve gövde bulunur. 
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        UserResponse userResponse = authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
        //201 Created, yeni bir kaynağın başarıyla oluşturulduğunu belirtir.
    }


  
    
}
