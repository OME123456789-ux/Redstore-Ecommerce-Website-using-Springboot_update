package com.example.Shopping.website.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shopping.website.Entity.users;
import com.example.Shopping.website.Service.usersService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class usersController {
	private final usersService usersService;

	public usersController(usersService usersService) {
		this.usersService = usersService;
	}

	@GetMapping
	public List<users> getAll() {
		return usersService.getAllUsers();
	}

	@GetMapping("/{id}")
	public Optional<users> getById(@PathVariable Long id) {
		return usersService.getUserById(id);
	}

	@GetMapping("/by-username/{username}")
	public Optional<users> getByUsername(@PathVariable String username) {
		return usersService.getByUsername(username);
	}

	@PostMapping
	public users create(@RequestBody users user) {
		// Check if username already exists
		if (usersService.getByUsername(user.getUsername()).isPresent()) {
			throw new RuntimeException("Username already exists");
		}
		return usersService.createUser(user);
	}

	@PutMapping("/{id}")
	public users update(@PathVariable Long id, @RequestBody users user) {
		return usersService.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		usersService.deleteUser(id);
	}

	@PostMapping("/login")
	public users login(@RequestBody Map<String, String> payload) {
		String username = payload.get("username");
		String password = payload.get("password");
		return usersService.login(username, password);
	}
}
