package com.example.Shopping.website.Repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Shopping.website.Entity.users;

@Repository
public interface usersRepositry extends JpaRepository<users, Long> {
	Optional<users> findByUsername(String username);
	boolean existsByUsername(String username);
}
