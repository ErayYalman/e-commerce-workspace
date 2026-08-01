package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.impl;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.PageResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.UserResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.User;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception.UserNotFoundException;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.mapper.UserMapper;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.repository.UserRepository;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.IUserService;

import lombok.RequiredArgsConstructor;


@Service
@Transactional 
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<UserResponse> getAllUsers(Pageable pageable) {
         Page<User> userPage = userRepository.findAll(pageable);
         return PageResponse.<UserResponse>builder()
                 .content(userPage.getContent().stream().map(userMapper::toResponse).toList())
                 .page(userPage.getNumber())
                 .size(userPage.getSize())
                 .totalElements(userPage.getTotalElements())
                 .totalPages(userPage.getTotalPages())
                 .first(userPage.isFirst())
                 .last(userPage.isLast())
                 .build();
    }


    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return userMapper.toResponse(user);
    }


    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        userRepository.delete(user);
    }
    
}
