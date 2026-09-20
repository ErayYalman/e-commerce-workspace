package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.OrderItemResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.OrderItem;


@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(target = "totalPrice", ignore = true)
    OrderItemResponse toResponse(OrderItem orderItem);

}
