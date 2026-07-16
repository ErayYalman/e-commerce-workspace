package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String sku;

    @Size(max = 1000)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

}