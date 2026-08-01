package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.PageResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.UserResponse;

public interface IUserController {
    
    PageResponse<UserResponse> getAllUsers(Pageable pageable);

    UserResponse getUserById(UUID userId);

  //  UserResponse updateUser(UUID userId, UpdateUserRequest userRequest);

    void deleteUser(UUID userId);

}
