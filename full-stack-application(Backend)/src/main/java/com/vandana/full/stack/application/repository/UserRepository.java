package com.vandana.full.stack.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vandana.full.stack.application.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
}