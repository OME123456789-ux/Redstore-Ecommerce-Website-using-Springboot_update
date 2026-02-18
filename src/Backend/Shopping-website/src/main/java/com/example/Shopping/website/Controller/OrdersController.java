package com.example.Shopping.website.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shopping.website.Entity.Orders;
import com.example.Shopping.website.Service.OrdersService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrdersController {
	private final OrdersService ordersService;

	public OrdersController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	@GetMapping("/{username}")
	public List<Orders> getOrders(@PathVariable String username) {
		return ordersService.getOrdersByUsername(username);
	}

	@GetMapping("/id/{orderId}")
	public Optional<Orders> getOrderById(@PathVariable Long orderId) {
		return ordersService.getOrderById(orderId);
	}

	@PostMapping
	public Orders placeOrder(@RequestBody Orders order) {
		return ordersService.placeOrder(order);
	}

	@DeleteMapping("/{orderId}")
	public void delete(@PathVariable Long orderId) {
		ordersService.deleteOrder(orderId);
	}
}
