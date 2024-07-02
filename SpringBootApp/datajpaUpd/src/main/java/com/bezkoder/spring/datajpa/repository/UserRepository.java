package com.bezkoder.spring.datajpa.repository;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findAll();
    User findUserByUserId(UUID userId);
	List<User> findByCalcount(String calcount);
	User findByEmail(String email);
}
