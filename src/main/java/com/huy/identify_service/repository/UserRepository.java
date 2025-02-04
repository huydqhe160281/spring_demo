package com.huy.identify_service.repository;

import com.huy.identify_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    //    check if username exists
    boolean existsByUsername(String username); // auto generate query by spring data jpa to check if username exists

    Optional<User> findByUsername(String username); // auto generate query by spring data jpa to find user by username
}
