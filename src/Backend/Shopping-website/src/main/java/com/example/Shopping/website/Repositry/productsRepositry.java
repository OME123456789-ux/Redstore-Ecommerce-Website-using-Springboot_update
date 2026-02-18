package com.example.Shopping.website.Repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Shopping.website.Entity.products;

@Repository
public interface productsRepositry extends JpaRepository<products, Long> {
	List<products> findByUsername(String username);
	List<products> findByCategory(String category);
}
