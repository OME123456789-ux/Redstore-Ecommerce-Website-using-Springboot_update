package com.example.Shopping.website.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Shopping.website.Entity.products;
import com.example.Shopping.website.Repositry.productsRepositry;

@Service
public class productsService {
	private final productsRepositry productsRepository;

	public productsService(productsRepositry productsRepository) {
		this.productsRepository = productsRepository;
	}

	public List<products> getAllProducts() {
		return productsRepository.findAll();
	}

	public Optional<products> getProductById(Long id) {
		return productsRepository.findById(id);
	}

	public List<products> getProductsByUsername(String username) {
		return productsRepository.findByUsername(username);
	}

	public List<products> getProductsByCategory(String category) {
		return productsRepository.findByCategory(category);
	}

	public products createProduct(products product) {
		return productsRepository.save(product);
	}

	public products updateProduct(Long id, products updated) {
		updated.setId(id);
		return productsRepository.save(updated);
	}

	public void deleteProduct(Long id) {
		productsRepository.deleteById(id);
	}
}
