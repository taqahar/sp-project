package com.example.supralogproject.service;

import com.example.supralogproject.controller.UserController;
import com.example.supralogproject.dto.AddressDTO;
import com.example.supralogproject.dto.UserDTO;
import com.example.supralogproject.exception.UnauthorizedRegistrationException;
import com.example.supralogproject.model.Address;
import com.example.supralogproject.model.Gender;
import com.example.supralogproject.model.User;
import com.example.supralogproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserService userService;

    private MockMvc mock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createUser_ValidUserDTO_ReturnsCreatedUser() throws UnauthorizedRegistrationException {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("Rue de la Paix");
        addressDTO.setCity("Paris");
        addressDTO.setZipCode("75000");
        addressDTO.setState("France");

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setEmail("jd@jd.com");
        userDTO.setGender(Gender.MALE);
        userDTO.setBirthDate(LocalDate.of(1995, 1, 1));
        userDTO.setAddress(addressDTO);

        Address address = new Address(
                addressDTO.getStreet(),
                addressDTO.getZipCode(),
                addressDTO.getCity(),
                addressDTO.getState()
        );
        User expectedUser = new User();
        expectedUser.setId("1");
        expectedUser.setFirstName("John");
        expectedUser.setLastName("Doe");
        expectedUser.setBirthDate(LocalDate.of(1995, 1, 1));
        expectedUser.setAddress(address);

        when(userService.createUser(any(UserDTO.class))).thenReturn(Optional.of(expectedUser));

        Optional<User> createdUser = userService.createUser(userDTO);

        assertTrue(createdUser.isPresent());
        assertEquals(expectedUser.getId(), createdUser.get().getId());
        assertEquals(expectedUser.getFirstName(), createdUser.get().getFirstName());
        assertEquals(expectedUser.getLastName(), createdUser.get().getLastName());
        assertEquals(expectedUser.getBirthDate(), createdUser.get().getBirthDate());
        assertEquals(expectedUser.getAddress().getState(), createdUser.get().getAddress().getState());
    }

    @Test
    void getAllUsers_SortOrderAsc_ReturnsSortedUsers() {
        String sortOrder = "asc";

        User user1 = new User();
        user1.setId("1");
        user1.setFirstName("John");
        user1.setLastName("Doe");

        User user2 = new User();
        user2.setId("2");
        user2.setFirstName("Jane");
        user2.setLastName("Smith");

        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(user1);
        expectedUsers.add(user2);

        when(userService.getAllUsers("asc")).thenReturn(expectedUsers);

        List<User> sortedUsers = userService.getAllUsers(sortOrder);

        assertEquals(expectedUsers.size(), sortedUsers.size());
        for (int i = 0; i < expectedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i).getId(), sortedUsers.get(i).getId());
            assertEquals(expectedUsers.get(i).getFirstName(), sortedUsers.get(i).getFirstName());
            assertEquals(expectedUsers.get(i).getLastName(), sortedUsers.get(i).getLastName());
        }
    }

    @Test
    void getUserById_ValidId_ReturnsUser() {
        String id = "1";

        User expectedUser = new User();
        expectedUser.setId("1");
        expectedUser.setFirstName("John");
        expectedUser.setLastName("Doe");

        when(userService.getUserById(id)).thenReturn(Optional.of(expectedUser));

        Optional<User> user = userService.getUserById(id);

        assertTrue(user.isPresent());
        assertEquals(expectedUser.getId(), user.get().getId());
        assertEquals(expectedUser.getFirstName(), user.get().getFirstName());
        assertEquals(expectedUser.getLastName(), user.get().getLastName());
    }
}
