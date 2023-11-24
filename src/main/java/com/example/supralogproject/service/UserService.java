package com.example.supralogproject.service;

import com.example.supralogproject.dto.UserDTO;
import com.example.supralogproject.exception.UnauthorizedRegistrationException;
import com.example.supralogproject.model.User;
import com.example.supralogproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
/**
 * Service class for user-related operations.
 * Provides functionality for user management including creation and retrieval.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final ModelMapper modelMapper;

    /**
     * Constructs a UserService with the provided ModelMapper.
     *
     * @param modelMapper The ModelMapper used for object mapping.
     */
    public UserService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param userDTO The UserDTO to be converted.
     * @return The converted User entity.
     */
    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user The User entity to be converted.
     * @return The converted UserDTO.
     */
    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    /**
     * Creates a user from a UserDTO.
     * Throws UnauthorizedRegistrationException for users under 18 or not in France.
     *
     * @param userDTO The UserDTO to create a user from.
     * @return Optional of the created User.
     * @throws UnauthorizedRegistrationException If user is underage or not in France.
     */
    public Optional<User> createUser(UserDTO userDTO) throws UnauthorizedRegistrationException {
        LocalDate today = LocalDate.now();
        Period period = Period.between(userDTO.getBirthDate(), today);

        if (period.getYears() < 18) {
            throw new UnauthorizedRegistrationException("User must be over 18 years old");
        }

        if (userDTO.getAddress() != null) {
            if (!userDTO.getAddress().getState().equalsIgnoreCase("france")) {
                throw new UnauthorizedRegistrationException("User must be in France");
            }
        }
        else {
            throw new UnauthorizedRegistrationException("User must have an address");
        }
        User userObJ = convertToEntity(userDTO);
        return Optional.of(userRepository.save(userObJ));
    }

    /**
     * Retrieves all users sorted by last name in the specified order.
     *
     * @param sortOrder The order for sorting ("asc" or "desc").
     * @return List of sorted Users.
     */
    public List<User> getAllUsers(String sortOrder) {
        if (sortOrder.equalsIgnoreCase("asc")) {
            return userRepository.findAllByOrderByLastNameAsc();
        } else {
            return userRepository.findAllByOrderByLastNameDesc();
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return Optional of the User.
     */
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
}
