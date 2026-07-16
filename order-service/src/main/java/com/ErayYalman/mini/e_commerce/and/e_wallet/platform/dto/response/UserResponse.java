package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

}
