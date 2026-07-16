package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.mapper;

import org.mapstruct.Mapper;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.RegisterRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.UserResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequest registerRequest);

    UserResponse toResponse(User user);
    
}
