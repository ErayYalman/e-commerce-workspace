package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.User;


public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
