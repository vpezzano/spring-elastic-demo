package com.valpez.springelasticdemo.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valpez.springelasticdemo.model.User;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>{
}
