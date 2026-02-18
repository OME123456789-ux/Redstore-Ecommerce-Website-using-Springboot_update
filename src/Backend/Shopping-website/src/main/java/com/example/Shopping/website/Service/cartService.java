package com.example.Shopping.website.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Shopping.website.Entity.cart;
import com.example.Shopping.website.Repositry.cartRepositry;

@Service
public class cartService {
	private final cartRepositry cartRepository;

	public cartService(cartRepositry cartRepository) {
		this.cartRepository = cartRepository;
	}

	public List<cart> getCartByUsername(String username) {
		// Only return ACTIVE cart items (not ordered or removed)
		return cartRepository.findByUsernameAndStatus(username, "ACTIVE");
	}

	public cart addCartItem(cart item) {
		try {
			// Set default values if not provided
			if (item.getQuantity() == null) {
				item.setQuantity(1);
			}
			if (item.getAddedAt() == null) {
				item.setAddedAt(java.time.LocalDateTime.now());
			}
			return cartRepository.save(item);
		} catch (Exception e) {
			throw new RuntimeException("Failed to save cart item: " + e.getMessage());
		}
	}

	public Optional<cart> getById(Long id) {
		return cartRepository.findById(id);
	}

	public cart updateQuantity(Long id, Integer quantity) {
		cart existing = cartRepository.findById(id).orElseThrow();
		existing.setQuantity(quantity);
		return cartRepository.save(existing);
	}

	public Optional<cart> updateCartItemQuantity(Long id, Integer quantity) {
		Optional<cart> existing = cartRepository.findById(id);
		if (existing.isPresent()) {
			cart item = existing.get();
			item.setQuantity(quantity);
			return Optional.of(cartRepository.save(item));
		}
		return Optional.empty();
	}

	@Transactional
	public void clearCartForUsername(String username) {
		// Delete all ACTIVE items for the user using bulk delete
		cartRepository.deleteByUsernameAndStatus(username, "ACTIVE");
	}

	public void removeCartItem(Long id) {
		// Delete the cart item
		cartRepository.deleteById(id);
	}

	public List<cart> getCartHistoryByUsername(String username) {
		// Return all cart items (ACTIVE, ORDERED, REMOVED)
		return cartRepository.findByUsername(username);
	}

	public List<cart> getOrderedItemsByUsername(String username) {
		// Return only ORDERED items
		return cartRepository.findByUsernameAndStatus(username, "ORDERED");
	}

	@Transactional
	public int markCartAsOrdered(String username) {
		return cartRepository.updateStatusByUsernameAndStatus(username, "ACTIVE", "ORDERED");
	}
}
