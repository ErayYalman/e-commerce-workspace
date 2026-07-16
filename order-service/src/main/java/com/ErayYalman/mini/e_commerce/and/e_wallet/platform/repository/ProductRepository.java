package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    
}
