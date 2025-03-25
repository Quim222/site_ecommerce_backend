package com.eCommerce.shoppingSite.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eCommerce.shoppingSite.DTO.UserDTO;
import com.eCommerce.shoppingSite.repo.UserRepository;
import com.eCommerce.shoppingSite.tables.user.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    public UserDTO createUser(User user) {
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public String getRoleByEmail(String email) {
        return userRepository.findRoleByEmail(email);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getNome(), user.getEmail(), user.getRole());
    }
}
