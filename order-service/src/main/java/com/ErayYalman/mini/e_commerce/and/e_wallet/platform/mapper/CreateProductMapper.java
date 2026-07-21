package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.CreateProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.UpdateProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.ProductResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.Product;

@Mapper(componentModel = "spring")
public interface CreateProductMapper {

    Product toEntity(CreateProductRequest productRequest);

    ProductResponse toResponse(Product product);

    void updateProductFromRequest(UpdateProductRequest productRequest, @MappingTarget Product product);
    //mappingTarget kullanarak var olan bir nesneyi güncelleyebiliriz. Bu sayede yeni bir nesne oluşturmak yerine mevcut nesneyi güncelleyebiliriz.
    
}
