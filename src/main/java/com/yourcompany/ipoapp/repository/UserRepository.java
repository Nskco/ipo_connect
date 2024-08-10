package com.yourcompany.ipoapp.repository;

import com.yourcompany.ipoapp.model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
    
}
