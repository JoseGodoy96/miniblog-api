package com.chema.db.miniblog.repository;

import com.chema.db.miniblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
