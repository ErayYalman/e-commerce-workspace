package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.LoginRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.RegisterRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.UserResponse;

public interface IUserController {
    
    UserResponse registerUser(RegisterRequest registerRequest);

    UserResponse loginUser(LoginRequest loginRequest);

}
