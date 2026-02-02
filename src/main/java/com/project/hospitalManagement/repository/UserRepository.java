package com.project.hospitalManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalManagement.entity.User;
import com.project.hospitalManagement.entity.type.AuthProviderType;

public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByUsername(String username);
    Optional<User> findByProviderIdAndProviderType(String providerId,AuthProviderType providerType);
}
