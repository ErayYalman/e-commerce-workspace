package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByUserId(UUID userId);
    
}
