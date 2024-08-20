package com.yourcompany.ipoapp.service;

import com.yourcompany.ipoapp.model.User_data;
import com.yourcompany.ipoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User_data> getAllUsers() {
        return userRepository.findAll();
    }

    public User_data findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User_data> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User_data saveUser(User_data user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    public User_data addUserInterest(String username, String company) {
        User_data user = userRepository.findByUsername(username).orElse(null);

        System.out.println(username);
        if (user != null) {
            user.getInterestedIpoIds().put(company,"0");
            user.getLiveDeals().put(username, company+ " STATUS: Not Paired");
            return userRepository.save(user);
        }
        return null;
    }

    public void removeIpoFromUsers(String ipoId) {
        List<User_data> users = userRepository.findAll();
        for (User_data user : users) {
            if (user.getInterestedIpoIds().containsKey(ipoId)) {
                user.getInterestedIpoIds().remove(ipoId);
                userRepository.save(user);
            }
        }
    }

    public HashMap<String,String> getInterestedIPO(String un){
        User_data user = userRepository.findByUsername(un).orElse(null);
        HashMap<String,String> list= user.getInterestedIpoIds();
        if(list.isEmpty()){
            return null;
            
        }
        return list;
    } 

 
}
