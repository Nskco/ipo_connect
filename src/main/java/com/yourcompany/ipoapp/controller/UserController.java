package com.yourcompany.ipoapp.controller;

import com.yourcompany.ipoapp.model.User_data;
import com.yourcompany.ipoapp.service.IPOService;
import com.yourcompany.ipoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User_data> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public  User_data getUserById(@PathVariable String id,Principal principal) {
        if(principal.getName()!=userService.findById(id).getUsername()){
            return null;
        }
        return userService.findById(id);
    }

    @GetMapping("/username/{username}")
    public User_data getUserByUsername(@PathVariable String username) {
        return userService.getByUsername(username).orElse(null);
    }

    @PostMapping("/register")
    public User_data registerUser(@RequestBody User_data user) {
        return userService.saveUser(user);
    }

    @Autowired
    private IPOService ipoService;

    @PostMapping("/interested/{companyName}")
    public ResponseEntity<String> addInterest(@PathVariable String companyName, Principal principal) {
        String username = principal.getName();

        if (!ipoService.ipoExists(companyName)) {
            return ResponseEntity.ok("There's no IPO live with name " + companyName);
        }
        if (userService.getByUsername(username) == null) {
            return ResponseEntity.ok("User not registered");
        }
        if (userService.addUserInterest(username, companyName) == null) {
            return ResponseEntity.ok("Failed");
        }

        return ResponseEntity.ok("Interest added successfully.");
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username, Principal principal) {
        if (!principal.getName().equals(username)) {
            return ResponseEntity.status(403).body("Access Denied");
        }

        userService.deleteUser(username);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/interestedIPO")
    public HashMap<String, String> getInterestedIPO(Principal principal) {
        String username = principal.getName();
        return userService.getInterestedIPO(username);
    }

    @PostMapping("/pair/{company}")
    public String pair(@RequestParam String patner, @PathVariable String company, Principal principal) {
        String username = principal.getName();

        User_data user = userService.getByUsername(patner).orElse(null);
        User_data mainUser = userService.getByUsername(username).orElse(null);

        if (user == null) {
            return "Sorry, failed to get info about user";
        }
        if (!user.getInterestedIpoIds().containsKey(company)) {
            return "The other user is not interested in the deal";
        }

        if (!mainUser.getInterestedIpoIds().get(company).equals("0")) {
            return "You are already paired for this IPO";
        }
        if (user.getInterestedIpoIds().get(company).equals("0")) {
            mainUser.getInterestedIpoIds().put(company, "Paired to " + patner + " at your 1st preference");
            mainUser.setLiveDeals(username, company + " Already paired");
            userService.saveUser(mainUser);

            user.getInterestedIpoIds().put(company, "Paired to " + username + " at your 2nd preference");
            user.setLiveDeals(patner, company + " Already paired");
            userService.saveUser(user);
        } else {
            return "The user " + patner + " is already paired with another user";
        }

        return "Paired Successfully";
    }

    @GetMapping("/deals")
    public HashMap<String, String> getStatusOfDeal(Principal principal) {
        String username = principal.getName();
        User_data user = userService.getByUsername(username).orElse(new User_data());
        return user.getLiveDeals();
    }


    @PostMapping("/rating/{username}/{r}")
    public String addRatings(Principal principal,@PathVariable("username") String username,@PathVariable("r") int r)
    {
        if(r>5||r<0){
            return "Rate between 1 to 5";
        }
        User_data ud=userService.getByUsername(username).orElse(null);
        if(ud!=null){
            if(ud.getRatingProviders().add(principal.getName())){
                int length=ud.getRatingProviders().size();
                int rt=ud.getRatings()+r;
                ud.setRatings(rt/length);
                userService.saveUser(ud);
                return "Rating added successfully! Thank you";
            }
            else{
                return "You have already rated the following user";
            }
            
        }
        return "User not found";
    }
    @GetMapping("/rating/{username}")
    public String showRating(@PathVariable String username) {
        User_data ud=userService.getByUsername(username).orElse(null);

        if(ud==null){
            return "User not found";
        }
        else{
        String ratings="Ratings :"+ud.getRatings();
return ratings;    }
}
}
