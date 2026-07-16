package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.OutboxEvent;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, UUID> {
    
}
