package com.yourcompany.ipoapp.repository;

import com.yourcompany.ipoapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
