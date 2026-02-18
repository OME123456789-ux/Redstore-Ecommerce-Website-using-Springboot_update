package com.example.Shopping.website.Repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Shopping.website.Entity.Orders;

@Repository
public interface OrdersRepositry extends JpaRepository<Orders, Long> {
	List<Orders> findByUsername(String username);
}
