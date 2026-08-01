package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.LoginRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.RegisterRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.LoginResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.UserResponse;

public interface IAuthService {

    UserResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);
}
