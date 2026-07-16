package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    
    @NotNull
    private UUID productId;

    @NotNull
    @Min(1)
    private Integer quantity;

}
