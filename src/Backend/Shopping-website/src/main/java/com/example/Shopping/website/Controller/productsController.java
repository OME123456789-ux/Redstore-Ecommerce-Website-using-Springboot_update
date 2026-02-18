package com.example.Shopping.website.Controller;

import java.util.List;
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

import com.example.Shopping.website.Entity.products;
import com.example.Shopping.website.Service.productsService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class productsController {
	private final productsService productsService;

	public productsController(productsService productsService) {
		this.productsService = productsService;
	}

	@GetMapping
	public List<products> getAll() {
		return productsService.getAllProducts();
	}

	@GetMapping("/{id}")
	public Optional<products> getById(@PathVariable Long id) {
		return productsService.getProductById(id);
	}

	@GetMapping("/by-username/{username}")
	public List<products> getByUsername(@PathVariable String username) {
		return productsService.getProductsByUsername(username);
	}

	@GetMapping("/by-category/{category}")
	public List<products> getByCategory(@PathVariable String category) {
		return productsService.getProductsByCategory(category);
	}

	@PostMapping
	public products create(@RequestBody products product) {
		return productsService.createProduct(product);
	}

	@PutMapping("/{id}")
	public products update(@PathVariable Long id, @RequestBody products product) {
		return productsService.updateProduct(id, product);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		productsService.deleteProduct(id);
	}
}
