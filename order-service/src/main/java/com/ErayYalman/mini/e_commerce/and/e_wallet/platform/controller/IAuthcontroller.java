package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller;

import org.springframework.http.ResponseEntity;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.LoginRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.LoginResponse;

public interface IAuthcontroller {

   ResponseEntity<LoginResponse> login(LoginRequest loginRequest);
    
}
