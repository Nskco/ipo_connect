package com.yourcompany.ipoapp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompany.ipoapp.model.IPO;
import com.yourcompany.ipoapp.service.IPOService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    private final IPOService ipoService;
    
    public AdminController(IPOService ipoService){
        this.ipoService = ipoService;
    }

    @GetMapping
    public String home(){
        return "Hello Admin!!";
    }    

    @DeleteMapping("/delete/{name}")
    public IPO delete(@PathVariable String name){
        String Uname = name.toUpperCase();
        return ipoService.delete(Uname);
    }
    
    @DeleteMapping("/deleteAll")
    public void deleteAllIPOs() {
        ipoService.deleteAllIPOs();
    }

    @PostMapping
    public IPO createIPO(@RequestBody IPO ipo) throws IllegalArgumentException {
        return ipoService.save(ipo);
    }
    
}
