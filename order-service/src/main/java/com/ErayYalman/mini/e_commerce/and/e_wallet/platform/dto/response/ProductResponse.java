package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private UUID id;
    
    private String name;

    private String sku;

    private String description;

    private BigDecimal price;

}
