package com.chema.db.miniblog.service;

import com.chema.db.miniblog.exception.ResourceNotFoundException;
import com.chema.db.miniblog.model.User;
import com.chema.db.miniblog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
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
