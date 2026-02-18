package com.example.Shopping.website;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Shopping.website.Entity.products;
import com.example.Shopping.website.Service.productsService;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private productsService productsService;

    @Override
    public void run(String... args) throws Exception {
        // Check if products already exist
        if (productsService.getAllProducts().isEmpty()) {
            initializeProducts();
        }
    }

    private void initializeProducts() {
        List<products> sampleProducts = Arrays.asList(
            // Electronics
            createProduct("admin", "iPhone 15 Pro", "Latest iPhone with A17 Pro chip", new BigDecimal("159999"), 50, "Electronics", "../../assests/Iphone.jpeg"),
            createProduct("admin", "MacBook Air M2", "Powerful laptop with M2 chip", new BigDecimal("89999"), 30, "Electronics", "../../assests/MacBook Air M2.jpeg"),
            createProduct("admin", "Samsung Galaxy S24", "Latest Samsung with AI features", new BigDecimal("15999"), 40, "Electronics", "../../assests/Samsung Galaxy S24.jpeg"),
            createProduct("admin", "Sony WH-1000XM4 Headphones", "Premium noise cancelling headphones", new BigDecimal("29999"), 25, "Electronics", "../../assests/Sony WH-1000XM4 Headphones.jpeg"),
            createProduct("admin", "Samsung Galaxy Watch 6", "Smart fitness watch with health tracking", new BigDecimal("12999"), 35, "Electronics", "../../assests/Samsung Galaxy Watch 6.jpeg"),
            createProduct("admin", "iPad Pro 12.9 (M2)", "Professional iPad with M2 chip", new BigDecimal("45999"), 20, "Electronics", "../../assests/iPad Pro.jpeg"),
            createProduct("admin", "JBL Flip 6 Portable Speaker", "Portable waterproof speaker", new BigDecimal("8999"), 45, "Electronics", "../../assests/JBL Flip 6 Portable Speaker.jpeg"),
            createProduct("admin", "Philips Coffee Maker", "Programmable coffee maker", new BigDecimal("7999"), 30, "Electronics", "../../assests/product-2.jpg"),
            createProduct("admin", "Philips Air Purifier", "Advanced air purification system", new BigDecimal("14999"), 15, "Electronics", "../../assests/Philips Air Purifier.jpeg"),
            
            // Fashion
            createProduct("admin", "Nike Air Max 270", "Comfortable Nike Air Max shoes", new BigDecimal("2999"), 60, "Fashion", "../../assests/Nike Air Max 270.jpeg"),
            createProduct("admin", "Adidas Originals T-Shirt", "Classic Adidas cotton t-shirt", new BigDecimal("1499"), 80, "Fashion", "../../assests/Adidas Originals T-Shirt.jpeg"),
            createProduct("admin", "Levis 501 Original Jeans", "Classic fit denim jeans", new BigDecimal("3999"), 55, "Fashion", "../../assests/Levis 501 Original Jeans.jpeg"),
            createProduct("admin", "Zara Cotton T-Shirt", "Premium cotton t-shirt", new BigDecimal("899"), 70, "Fashion", "../../assests/zara t shirt.jpeg"),
            createProduct("admin", "WROGN Formal Shirt", "Stylish formal shirt", new BigDecimal("2499"), 40, "Fashion", "../../assests/WROGN Formal Shirt.jpeg"),
            createProduct("admin", "Red Tape Casual Shoes", "Comfortable casual shoes", new BigDecimal("5999"), 35, "Fashion", "../../assests/redtape shoes"),
            
            // Home & Living
            createProduct("admin", "Modern 3-Seater Sofa", "Comfortable modern sofa for your living room", new BigDecimal("8999"), 20, "Home & Living", "../../assests/Modern 3-Seater Sofa.jpeg"),
            createProduct("admin", "IKEA LED Table Lamp", "Modern LED table lamp", new BigDecimal("5999"), 40, "Home & Living", "../../assests/IKEA LED Table Lamp.jpeg"),
            createProduct("admin", "IKEA Storage Unit", "Versatile storage solution", new BigDecimal("3999"), 25, "Home & Living", "../../assests/product-3.jpg"),
            
            // Beauty & Personal Care
            createProduct("admin", "L'Oreal Paris Skincare Set", "Complete skincare set for glowing skin", new BigDecimal("2499"), 50, "Beauty", "../../assests/Oreal Paris Skincare Set.jpeg"),
            createProduct("admin", "Maybelline Makeup Kit", "Complete makeup kit for everyday use", new BigDecimal("1899"), 60, "Beauty", "../../assests/Maybelline Makeup Kit.jpeg"),
            createProduct("admin", "Lakme Hair Care Set", "Complete hair care with natural ingredients", new BigDecimal("3299"), 45, "Beauty", "../../assests/Lakme Hair Care Set.jpeg"),
            createProduct("admin", "Nykaa Lipstick Set", "Set of 5 long-lasting lipsticks", new BigDecimal("1599"), 70, "Beauty", "../../assests/product-4.jpg"),
            createProduct("admin", "Garnier Skin Naturals", "Natural skincare collection", new BigDecimal("2799"), 40, "Beauty", "../../assests/product-5.jpg"),
            
            // Deals & Offers
            createProduct("admin", "Black Friday Deal", "Amazing deals on electronics", new BigDecimal("399"), 100, "Deals", "../../assests/black friday.jpeg"),
            createProduct("admin", "Fashion Bonanza", "Trendy fashion at great prices", new BigDecimal("449"), 80, "Deals", "../../assests/trendy.jpeg"),
            createProduct("admin", "BOGO Deal", "Buy one get one free offer", new BigDecimal("399"), 120, "Deals", "../../assests/get1.jpeg"),
            createProduct("admin", "New Arrival", "Latest smartphone with amazing features", new BigDecimal("1999"), 30, "Deals", "../../assests/phone.jpeg"),
            createProduct("admin", "Premium Smartphone", "High-end smartphone with premium features", new BigDecimal("15999"), 25, "Deals", "../../assests/Premium Smartphone.jpeg"),
            createProduct("admin", "Wireless Headphones", "Premium wireless headphones", new BigDecimal("2499"), 40, "Deals", "../../assests/Wireless Headphones.jpeg"),
            createProduct("admin", "Gaming Laptop", "High-performance gaming laptop", new BigDecimal("45999"), 15, "Deals", "../../assests/Gaming Laptop.jpeg"),
            createProduct("admin", "Smart Watch", "Feature-rich smartwatch", new BigDecimal("1999"), 50, "Deals", "../../assests/Smart Watch.jpeg")
        );

        for (products product : sampleProducts) {
            try {
                productsService.createProduct(product);
            } catch (Exception e) {
                System.err.println("Failed to create product: " + product.getName() + " - " + e.getMessage());
            }
        }
        
        System.out.println("✅ Database initialized with " + sampleProducts.size() + " sample products!");
    }

    private products createProduct(String username, String name, String description, BigDecimal price, Integer stock, String category, String imageUrl) {
        products product = new products();
        product.setUsername(username);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setCategory(category);
        product.setImageUrl(imageUrl);
        return product;
    }
}


