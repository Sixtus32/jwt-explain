package com.s6x.jwtexplain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s6x.jwtexplain.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
