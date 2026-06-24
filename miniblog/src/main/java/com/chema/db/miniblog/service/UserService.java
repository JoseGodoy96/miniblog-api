package com.chema.db.miniblog.service;

import com.chema.db.miniblog.dto.UserRequest;
import com.chema.db.miniblog.dto.UserResponse;
import com.chema.db.miniblog.exception.ResourceNotFoundException;
import com.chema.db.miniblog.model.User;
import com.chema.db.miniblog.service.UserMapper;
import com.chema.db.miniblog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        return UserRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", id));
    }

    public UserResponse createUser(UserRequest request) {
        User user = UserMapper.toEntity(request);
        User savedUser = UserRepository.save(user);
        return UserMapper.toResponse(savedUser);
    }

    public User updateUser(Long id, User updatedUser) {

        return userRepository.findById(id)
                .map(user -> {

                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());

                    return userRepository.save(user);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
