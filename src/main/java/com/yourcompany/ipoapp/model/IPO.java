package com.yourcompany.ipoapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ipo1")
public class IPO {
    @Id
    private String id;
    @Indexed(unique = true)
    private String companyName;
    private int shares;
    private String sector;

    public IPO(String companyName, int shares, String sector) {
        this.companyName = companyName.toUpperCase(); // Store in uppercase
        this.shares = shares;
        this.sector=sector;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName.toUpperCase();
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }



    // Getters and Setters
}
