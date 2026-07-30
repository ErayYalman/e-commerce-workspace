package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Email is required")
    @Size(max = 255)
    @Email
    private String email; 

    @NotBlank(message = "Password is required")
    @Size(max = 255)
    private String password;

}
