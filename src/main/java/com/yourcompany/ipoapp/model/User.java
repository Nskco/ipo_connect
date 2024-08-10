package com.yourcompany.ipoapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private HashMap<String,String> interestedIpoIds=new HashMap<>(); // List of interested IPOs

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String,String> getInterestedIpoIds() {
        return interestedIpoIds;
    }

    public void setInterestedIpoIds(HashMap<String,String> interestedIpoIds) {
        this.interestedIpoIds = interestedIpoIds;
    }
}
