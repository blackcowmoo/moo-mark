package com.blackcowmoo.moomark.repository;

import java.util.Optional;

import com.blackcowmoo.moomark.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

}
