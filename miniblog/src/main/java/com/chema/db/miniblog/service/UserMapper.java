package com.chema.db.miniblog.service;

import com.chema.db.miniblog.dto.UserRequest;
import com.chema.db.miniblog.dto.UserResponse;
import com.chema.db.miniblog.model.User;

public class UserMapper {
    public static User toEntity(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        return user;
    }

    public static UserResponse toResponse(User User) {
        UserResponse response = new UserResponse();
        response.setId(User.getId());
        response.setUsername(User.getUsername());
        response.setEmail(User.getEmail());
        return response;
    }
}
