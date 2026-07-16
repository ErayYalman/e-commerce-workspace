package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    @NotEmpty
    @Valid
    private List<OrderItemRequest> orderItems;

}