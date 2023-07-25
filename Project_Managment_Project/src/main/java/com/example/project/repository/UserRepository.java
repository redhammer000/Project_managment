package com.example.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findUserByEmail(String Email);


}
