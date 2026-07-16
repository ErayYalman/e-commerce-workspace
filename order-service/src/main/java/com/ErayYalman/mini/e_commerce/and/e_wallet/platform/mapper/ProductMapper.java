package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.mapper;

import org.mapstruct.Mapper;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.ProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.ProductResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequest productRequest);

    ProductResponse toResponse(Product product);
    
}
