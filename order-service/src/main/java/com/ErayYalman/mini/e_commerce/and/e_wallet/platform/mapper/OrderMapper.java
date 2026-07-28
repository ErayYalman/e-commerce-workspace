package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.mapper;

import org.mapstruct.Mapper;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.OrderResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.Order;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class) //uses = OrderItemMapper.class ekledik çünkü OrderItemMapper'ı kullanacağız
public interface OrderMapper {
    
    OrderResponse toResponse(Order order);

}
