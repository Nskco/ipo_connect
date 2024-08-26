package com.yourcompany.ipoapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "user")
public class User_data {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private HashMap<String,String> interestedIpoIds=new HashMap<>(); // List of interested IPOs
    private static HashMap<String,String> liveDeals=new HashMap<>();
    private int nooOfDealsparticipated;
    private int ratings;
    private HashSet<String> ratingProviders=new HashSet<>();
    public User_data(){

    }


 
    public HashMap<String,String> getLiveDeals() {
        return liveDeals;
    }

    public void setLiveDeals(String userName,String Status) {
        liveDeals.put(userName, Status);
    }

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

    public HashSet<String> getRatingProviders() {
        return ratingProviders;
    }
    public void setRatingProviders(String username) {
        ratingProviders.add(username);
    }
    
 
   
    public int getRatings() {
        return ratings;
    }

    
    public void setRatings(int ratings) {
        this.ratings = ratings;
    }


    /**
     * @return int return the nooOfDealsparticipated
     */
    public int getNooOfDealsparticipated() {
        return nooOfDealsparticipated;
    }

    /**
     * @param nooOfDealsparticipated the nooOfDealsparticipated to set
     */
    public void setNooOfDealsparticipated(int nooOfDealsparticipated) {
        this.nooOfDealsparticipated = nooOfDealsparticipated;
    }


   

}
