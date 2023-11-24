package com.example.supralogproject.controller;

import com.example.supralogproject.dto.UserDTO;
import com.example.supralogproject.model.User;
import com.example.supralogproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_shouldReturnCreatedUser() {
        UserDTO userDTO = new UserDTO();
        User createdUser = new User();
        when(userService.createUser(userDTO)).thenReturn(Optional.of(createdUser));

        ResponseEntity<User> response = userController.createUser(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdUser, response.getBody());
        verify(userService, times(1)).createUser(userDTO);
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        String sortOrder = "asc";
        List<User> userList = Arrays.asList(new User(), new User());
        when(userService.getAllUsers(sortOrder)).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAllUsers(sortOrder);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
        verify(userService, times(1)).getAllUsers(sortOrder);
    }

    @Test
    void getUserById_shouldReturnUserIfExists() {
        String userId = "123";
        User user = new User();
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void getUserById_shouldReturnNotFoundIfUserDoesNotExist() {
        String userId = "123";
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getUserById(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).getUserById(userId);
    }
}
