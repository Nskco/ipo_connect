package com.yourcompany.ipoapp.controller;

import com.yourcompany.ipoapp.model.User;
import com.yourcompany.ipoapp.service.IPOService;
import com.yourcompany.ipoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getByUsername(username).orElse(null);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }




    @Autowired
    private IPOService ipoService;
    @PostMapping("/interested/{companyName}")
    public ResponseEntity<String> addInterest(@RequestParam String username, @PathVariable String companyName) {
        // Assuming IPO ID is the same as companyName for simplicity
        if(!ipoService.ipoExists(companyName)){
            System.out.println("There's no IPO live with name "+companyName);
        } 
        if(userService.getByUsername(username)==null){
            System.out.println("User not registered");
        }
        userService.addUserInterest(username, companyName);
        
        return ResponseEntity.ok("Interest added successfully.");
        
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/interestedIPO/{username}")
    public HashMap<String,String> getInterestedIPO(@PathVariable String username) {
        return userService.getInterestedIPO(username);
    }

    @PostMapping("/pair/{username}/{company}")
    public String pairr(@RequestParam String patner,@PathVariable String username,@PathVariable String company){
        User user=userService.getByUsername(patner).orElse(null);
        User mainUser=userService.getByUsername(username).orElse(null);

        if(user==null){
            return "Sorry failed to get info about user";
        }
        if(!user.getInterestedIpoIds().containsKey(company)){
            return "The other user not intrested in the deal";
        }

        if(!mainUser.getInterestedIpoIds().get(company).equals("0")){
            return "You are already paired for this IPO";
        }
        if(user.getInterestedIpoIds().get(company).equals("0")){
            mainUser.getInterestedIpoIds().put(company, "Paired to "+patner+" at your 1st prefernce");
            userService.saveUser(mainUser);

            user.getInterestedIpoIds().put(company, "Paired to "+ username+" at your 2nd prefernce");
            userService.saveUser(user);
        }
        else{
            return "The user "+patner+"   is already paired with other user";
        }
        
        return "Paired Successfully";
    }
}
