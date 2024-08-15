package com.yourcompany.ipoapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterEach;

import com.yourcompany.ipoapp.model.User;
import com.yourcompany.ipoapp.repository.UserRepository;
import com.yourcompany.ipoapp.service.UserService;

@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    public 
    void setUp() {
        user = new User();
        user.setEmail("Test");
        user.setUsername("Testt");
        user.setPassword("Test");

        userRepository.save(user);
    }

    @Test
    void shouldFindUserByUsername() {
        User foundUser = userRepository.findByUsername("Testt").orElse(null);
        assertEquals(user.getEmail(), foundUser.getEmail());
    }
    



    //  @TestFactory
    // Stream<DynamicTest> shouldFindUsersByUsernames() {
    //     List<String> usernames = Arrays.asList("Testt", "AnotherUsername", "YetAnotherUsername");

    //     return usernames.stream()
    //             .map(username -> DynamicTest.dynamicTest("Test for username: " + username,
    //                 () -> {
    //                     // Add a user with the username to the repository if not exists
    //                     if (username.equals("Testt")) {
    //                         userRepository.save(user);
    //                     } else {
    //                         User newUser = new User();
    //                         newUser.setEmail("DifferentEmail");
    //                         newUser.setUsername(username);
    //                         newUser.setPassword("DifferentPassword");
    //                         userRepository.save(newUser);
    //                     }

    //                     User foundUser = userRepository.findByUsername(username).orElse(null);
    //                     if (username.equals("Testt")) {
    //                         assertEquals(user.getEmail(), foundUser.getEmail());
    //                     } else {
    //                         assertEquals("DifferentEmail", foundUser.getEmail());
    //                     }

    //                     // Clean up the repository
    //                     userRepository.deleteByUsername(username);
    //                 })
    //             );
    // }
    
    @AfterEach
    void clean(){
        userRepository.deleteByUsername("Testt");
    }
}