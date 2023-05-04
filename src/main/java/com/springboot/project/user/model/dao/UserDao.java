package com.springboot.project.user.model.dao;

import com.springboot.project.user.model.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
