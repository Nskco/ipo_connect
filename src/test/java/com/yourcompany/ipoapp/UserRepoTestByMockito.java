package com.yourcompany.ipoapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.yourcompany.ipoapp.model.User;
import com.yourcompany.ipoapp.repository.UserRepository;
import com.yourcompany.ipoapp.service.UserService;

@SpringBootTest
public class UserRepoTestByMockito {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("Test");
        user.setUsername("Test");
        user.setPassword("Test");

        // Mock the save operation
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Mock the find operation
        when(userRepository.findByUsername("Test")).thenReturn(java.util.Optional.of(user));

        userRepository.save(user);
    }

    @Test
    void shouldFindUserByUsername() {
        User foundUser = userService.getByUsername("Test").orElse(null);
        assertEquals(user.getEmail(), foundUser.getEmail());
    }

    @AfterEach
    void clean() {
        // Mock the delete operation
        doNothing().when(userRepository).deleteByUsername("Test");

        userRepository.deleteByUsername("Test");
    }
}
