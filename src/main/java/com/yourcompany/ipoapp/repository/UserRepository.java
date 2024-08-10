package com.yourcompany.ipoapp.repository;

import com.yourcompany.ipoapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    void deleteByUsername(String username);
}
