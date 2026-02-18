package com.example.Shopping.website.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "username", nullable = false, length = 100)
	private String username;

	@Column(name = "payment_method", nullable = false, length = 50)
	private String paymentMethod;

	@Column(name = "full_name", length = 200)
	private String fullName;

	@Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
	private BigDecimal totalAmount;

	// Total quantity of items in this order
	@Column(name = "quantity", nullable = false)
	private Integer quantity;


	@Column(name = "status", length = 50)
	private String status; // e.g., PENDING, DELIVERED

	// Added basic product details snapshot for each order
	@Column(name = "product_name", length = 1000)
	private String productName; // can contain comma-separated product names

	@Column(name = "product_rate", precision = 12, scale = 2)
	private BigDecimal productRate; // snapshot of rate/price information

	@Column(name = "product_image", length = 500)
	private String productImage; // snapshot of product image path

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	// Public-facing order number (8+ digits), unique
	@Column(name = "order_number", length = 32, unique = true)
	private String orderNumber;

	// Delivery address snapshot captured at order time
	@Column(name = "delivery_address", length = 2000)
	private String deliveryAddress;

	public Orders() {
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public BigDecimal getProductRate() {
		return productRate;
	}

	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@PrePersist
	protected void onCreate() {
		if (this.createdAt == null) {
			this.createdAt = LocalDateTime.now();
		}
		if (this.status == null || this.status.isBlank()) {
			this.status = "PENDING";
		}
		if (this.quantity == null || this.quantity < 1) {
			this.quantity = 1;
		}
		// Generate a unique public order number if not set
		if (this.orderNumber == null || this.orderNumber.isBlank()) {
			// 8-digit pseudo-random number based on epoch seconds and identity id fallback
			long ts = System.currentTimeMillis() / 1000L;
			long base = ts % 100000000L; // 8 digits
			long suffix = (long)(Math.random() * 100L); // 2 digits
			this.orderNumber = String.format("%08d%02d", base, suffix);
		}
	}
}
