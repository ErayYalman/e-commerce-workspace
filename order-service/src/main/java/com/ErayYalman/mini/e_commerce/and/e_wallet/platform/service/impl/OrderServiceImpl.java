package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.OrderItemRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.OrderRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.OrderResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.Order;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.OrderItem;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.Product;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.User;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.enums.OrderStatus;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception.InsufficientStockException;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception.OrderNotFoundException;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception.ProductNotFoundException;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.mapper.OrderMapper;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.repository.OrderRepository;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.repository.ProductRepository;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.security.CustomUserDetails;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.IOrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
@Transactional
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;


    @Override
    public OrderResponse createOrder(@AuthenticationPrincipal CustomUserDetails userDetails, OrderRequest orderRequest) {
        User user = userDetails.getUser();
        
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderItems(new ArrayList<>()); 
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(itemRequest.getProductId()));
            int quantity = itemRequest.getQuantity();
                    
            if(product.getStockQuantity() < quantity) {
            throw new InsufficientStockException(product.getId());
            }
        BigDecimal itemTotalPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        totalPrice = totalPrice.add(itemTotalPrice);
        product.setStockQuantity(product.getStockQuantity() - quantity);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setUnitPrice(product.getPrice());
        orderItem.setQuantity(quantity);

        order.getOrderItems().add(orderItem);   
    }
    order.setTotalPrice(totalPrice);
    Order savedOrder = orderRepository.save(order);
    return orderMapper.toResponse(savedOrder);
}

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        return orderMapper.toResponse(order);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByUserId(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return orderRepository.findAllByUserId(userDetails.getUser().getId())
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}
