package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.impl;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.LoginRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.RegisterRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.UserResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.User;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.enums.UserRole;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception.EmailAlreadyExistsException;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.mapper.UserMapper;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.repository.UserRepository;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.IUserService;


@Service
@Transactional 
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public UserResponse registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = userMapper.toEntity(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);

        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);

    }

    @Override
    @Transactional(readOnly = true) //sadece okuma işlemi yapacağımız için readOnly = true ekledik başka hiçbir işlem yapılmaz.
    public UserResponse loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return userMapper.toResponse(user);
    }
    
}
