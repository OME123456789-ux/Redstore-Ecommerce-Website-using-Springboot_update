package com.example.Shopping.website.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shopping.website.Entity.cart;
import com.example.Shopping.website.Service.cartService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cart")
public class cartController {
	private final cartService cartService;

	public cartController(cartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping("/{username}")
	public List<cart> getCart(@PathVariable String username) {
		return cartService.getCartByUsername(username);
	}

	@PostMapping
	public cart add(@RequestBody cart item) {
		try {
			if (item.getUsername() == null || item.getUsername().trim().isEmpty()) {
				throw new RuntimeException("Username is required");
			}
			if (item.getName() == null || item.getName().trim().isEmpty()) {
				throw new RuntimeException("Product name is required");
			}
			if (item.getPrice() == null || item.getPrice() <= 0) {
				throw new RuntimeException("Valid price is required");
			}
			return cartService.addCartItem(item);
		} catch (Exception e) {
			throw new RuntimeException("Failed to add item to cart: " + e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public cart updateQuantity(@PathVariable Long id, @RequestBody Map<String, Integer> payload) {
		return cartService.updateQuantity(id, payload.getOrDefault("quantity", 1));
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		cartService.removeCartItem(id);
	}

	@DeleteMapping("/clear/{username}")
	public void clear(@PathVariable String username) {
		cartService.clearCartForUsername(username);
	}

	@GetMapping("/history/{username}")
	public List<cart> getOrderHistory(@PathVariable String username) {
		return cartService.getCartHistoryByUsername(username);
	}

	@GetMapping("/ordered/{username}")
	public List<cart> getOrderedItems(@PathVariable String username) {
		return cartService.getOrderedItemsByUsername(username);
	}

	@PutMapping("/mark-ordered/{username}")
	public int markOrdered(@PathVariable String username) {
		return cartService.markCartAsOrdered(username);
	}
}
