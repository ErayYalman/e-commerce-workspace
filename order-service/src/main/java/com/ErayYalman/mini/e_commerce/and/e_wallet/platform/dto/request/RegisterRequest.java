package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.validation.annotation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(min = 8, max = 255, message = "Password must be at least 8 characters long")
    @ValidPassword
    private String password;

}
