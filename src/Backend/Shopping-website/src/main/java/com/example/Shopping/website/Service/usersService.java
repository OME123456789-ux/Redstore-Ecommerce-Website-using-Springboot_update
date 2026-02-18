package com.example.Shopping.website.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Shopping.website.Entity.users;
import com.example.Shopping.website.Repositry.usersRepositry;

@Service
public class usersService {
	private final usersRepositry usersRepository;

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
		return usersRepository.save(user);
	}

	public users updateUser(Long id, users updated) {
		updated.setId(id);
		return usersRepository.save(updated);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
}
