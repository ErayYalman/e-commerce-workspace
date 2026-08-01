package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.LoginRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.RegisterRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.LoginResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.UserResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.User;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.enums.UserRole;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception.EmailAlreadyExistsException;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.mapper.UserMapper;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.repository.UserRepository;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.security.CustomUserDetails;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.security.jwt.JwtService;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.IAuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthserviceImpl implements IAuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal(); 
        //getPrincipal() metodu, kimlik doğrulama işlemi başarılı olduğunda, doğrulanan kullanıcıyı temsil eden bir nesneyi döndürür.
        //object tipinde döner, bu yüzden CustomUserDetails tipine cast ediyoruz.
        User user = customUserDetails.getUser();
        String token = jwtService.generateToken(customUserDetails);

        return LoginResponse.builder()
                .token(token)
                .type("Bearer")
                .expiresAt(jwtService.getexpirationInstant()) //token'ın geçerlilik süresini ayarlıyoruz. yani token'ın ne kadar süre geçerli olacağını belirliyoruz.
                .user(userMapper.toResponse(user))
                .build(); 
    }

    @Override
    public UserResponse register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = userMapper.toEntity(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }
}
