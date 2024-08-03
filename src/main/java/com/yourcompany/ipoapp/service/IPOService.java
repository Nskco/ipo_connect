package com.yourcompany.ipoapp.service;

import com.yourcompany.ipoapp.model.IPO;
import com.yourcompany.ipoapp.repository.IPORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IPOService {

    @Autowired
    private IPORepository ipoRepository;

    public List<IPO> getAllIPOs() {
        return ipoRepository.findAll();
    }

    public IPO save(IPO ipo) throws IllegalArgumentException {
        // Check if the companyName already exists
        if (ipoRepository.existsByCompanyName(ipo.getCompanyName())) {
            throw new IllegalArgumentException("Company name must be unique");
        }
        return ipoRepository.save(ipo);
    }

    public IPO findById(String id) {
        return ipoRepository.findById(id).orElse(null);
    }
    public IPO getByCompanyName(String companyName) {
        Optional<IPO> optionalIPO = ipoRepository.findByCompanyName(companyName);
        return optionalIPO.orElse(null); // Return IPO if present, otherwise null
    }

    public void deleteAllIPOs() {
        ipoRepository.deleteAll();
    }
    public IPO delete(String name){
         IPO ipo=ipoRepository.findByCompanyName(name).orElse(null);
         if(ipo!=null){
             ipoRepository.deleteByCompanyName(name);
         }
         return ipo;
    }
}