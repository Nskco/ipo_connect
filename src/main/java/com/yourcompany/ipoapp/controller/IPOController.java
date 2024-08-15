package com.yourcompany.ipoapp.controller;

import com.yourcompany.ipoapp.model.IPO;
import com.yourcompany.ipoapp.service.IPOService;
import com.yourcompany.ipoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ipo")
public class IPOController {

    @Autowired
    private IPOService ipoService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<IPO> getAllIPOs() {
        return ipoService.getAllIPOs();
    }

    @GetMapping("/{id}")
    public IPO getIPOById(@PathVariable String id) {
        return ipoService.findById(id);
    }

    @GetMapping("/company/{cn}")
    public IPO getIPOByCompanyName(@PathVariable String cn) {
        String Cname = cn.toUpperCase();
        return ipoService.getByCompanyName(Cname);
    }

}
