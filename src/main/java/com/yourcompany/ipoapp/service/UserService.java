package com.yourcompany.ipoapp.service;

import com.yourcompany.ipoapp.model.User;
import com.yourcompany.ipoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    public User addUserInterest(String username, String company) {
        User user = userRepository.findByUsername(username).orElse(null);

        System.out.println(username);
        if (user != null) {
            user.getInterestedIpoIds().put(company,"0");
            user.getLiveDeals().put(username, company+ " STATUS: Not Paired");
            return userRepository.save(user);
        }
        return null;
    }

    public void removeIpoFromUsers(String ipoId) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getInterestedIpoIds().containsKey(ipoId)) {
                user.getInterestedIpoIds().remove(ipoId);
                userRepository.save(user);
            }
        }
    }

    public HashMap<String,String> getInterestedIPO(String un){
        User user = userRepository.findByUsername(un).orElse(null);
        HashMap<String,String> list= user.getInterestedIpoIds();
        if(list.isEmpty()){
            return null;
            
        }
        return list;
    } 

 
}
