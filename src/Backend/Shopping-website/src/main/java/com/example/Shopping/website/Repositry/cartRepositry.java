package com.example.Shopping.website.Repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Shopping.website.Entity.cart;

@Repository
public interface cartRepositry extends JpaRepository<cart, Long> {
	List<cart> findByUsername(String username);
	List<cart> findByUsernameAndStatus(String username, String status);
	List<cart> findByUsernameAndStatusNot(String username, String status);
	
	@Modifying
	@Query("DELETE FROM cart c WHERE c.username = :username AND c.status = :status")
	void deleteByUsernameAndStatus(@Param("username") String username, @Param("status") String status);
	
	@Modifying
	@Query("UPDATE cart c SET c.status = :newStatus WHERE c.username = :username AND c.status = :currentStatus")
	int updateStatusByUsernameAndStatus(
		@Param("username") String username,
		@Param("currentStatus") String currentStatus,
		@Param("newStatus") String newStatus);
}
