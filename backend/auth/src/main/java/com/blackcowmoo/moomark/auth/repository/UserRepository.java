package com.blackcowmoo.moomark.auth.repository;

import java.util.Optional;

import com.blackcowmoo.moomark.auth.model.AuthProvider;
import com.blackcowmoo.moomark.auth.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  Optional<User> findByName(String name);

  Optional<User> findByEmailAndAuthProvider(String email, AuthProvider authProvider);

  Optional<User> findByEmailAndName(String email, String name);

  Optional<User> findByNicknameLike(String nickname);

}
