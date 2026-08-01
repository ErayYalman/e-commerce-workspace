package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller.impl;


import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller.IUserController;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.PageResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.UserResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements IUserController {
        private final UserServiceImpl userService;

        public UserControllerImpl(UserServiceImpl userService) {
            this.userService = userService;
        }

        @Override
        @GetMapping("/all")
        @ResponseStatus(HttpStatus.OK)
        @PreAuthorize("hasRole('ADMIN')")
        public PageResponse<UserResponse> getAllUsers(Pageable pageable) {
            return userService.getAllUsers(pageable);
        }

        @Override
        @GetMapping("/{userId}")
        @ResponseStatus(HttpStatus.OK)
        public UserResponse getUserById(@PathVariable UUID userId) {
            return userService.getUserById(userId);
        }

        @Override
        @DeleteMapping("/{userId}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @PreAuthorize("hasRole('ADMIN')")
        public void deleteUser(@PathVariable UUID userId) {
            userService.deleteUser(userId);
        }

    

}
