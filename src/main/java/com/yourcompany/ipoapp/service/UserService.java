package com.yourcompany.ipoapp.service;

import com.yourcompany.ipoapp.model.User;
import com.yourcompany.ipoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
}
