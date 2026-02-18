package com.example.Shopping.website.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Shopping.website.Entity.users;
import com.example.Shopping.website.Repositry.usersRepositry;

@Service
public class usersService {
	private final usersRepositry usersRepository;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public usersService(usersRepositry usersRepository) {
		this.usersRepository = usersRepository;
	}

	public List<users> getAllUsers() {
		return usersRepository.findAll();
	}

	public Optional<users> getUserById(Long id) {
		return usersRepository.findById(id);
	}

	public Optional<users> getByUsername(String username) {
		return usersRepository.findByUsername(username);
	}

	public users createUser(users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return usersRepository.save(user);
	}

	public users updateUser(Long id, users updated) {
		updated.setId(id);
		if (updated.getPassword() != null && !updated.getPassword().isEmpty()) {
			updated.setPassword(passwordEncoder.encode(updated.getPassword()));
		}
		return usersRepository.save(updated);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}

	public users login(String username, String rawPassword) {
		return usersRepository.findByUsername(username)
				.filter(u -> u.getPassword() != null && passwordEncoder.matches(rawPassword, u.getPassword()))
				.orElseThrow(() -> new RuntimeException("Invalid credentials"));
	}
}
