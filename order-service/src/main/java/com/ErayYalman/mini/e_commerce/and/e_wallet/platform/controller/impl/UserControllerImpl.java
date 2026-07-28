package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller.impl;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller.IUserController;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.LoginRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.RegisterRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.UserResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.impl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements IUserController {
        private final UserServiceImpl userService;

        public UserControllerImpl(UserServiceImpl userService) {
            this.userService = userService;
        }

        @Override
        @PostMapping("/register")
        @ResponseStatus(HttpStatus.CREATED) 
        public UserResponse registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
            return userService.registerUser(registerRequest);
        }

        @Override
        @PostMapping("/login")
        @ResponseStatus(HttpStatus.OK)
        public UserResponse loginUser(@Valid @RequestBody LoginRequest loginRequest) {
            return userService.loginUser(loginRequest);
        }

    

}
