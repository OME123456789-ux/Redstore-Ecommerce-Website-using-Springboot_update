package com.example.Shopping.website.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Shopping.website.Entity.Orders;
import com.example.Shopping.website.Repositry.OrdersRepositry;

@Service
public class OrdersService {
	private final OrdersRepositry ordersRepository;

	public OrdersService(OrdersRepositry ordersRepository) {
		this.ordersRepository = ordersRepository;
	}

	public Orders placeOrder(Orders order) {
		return ordersRepository.save(order);
	}

	public Optional<Orders> getOrderById(Long orderId) {
		return ordersRepository.findById(orderId);
	}

	public List<Orders> getOrdersByUsername(String username) {
		return ordersRepository.findByUsername(username);
	}

	public void deleteOrder(Long orderId) {
		ordersRepository.deleteById(orderId);
	}
}
