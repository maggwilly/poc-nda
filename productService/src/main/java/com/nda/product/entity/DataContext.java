package com.nda.product.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class DataContext {
	private final Map<String, Product> productMap = new HashMap<>();
	@PostConstruct
	public void init() {
		productMap.put("P001", new Product("P001", "Product 1", "Description 1", 10.0));
		productMap.put("P002", new Product("P002", "Product 2", "Description 2", 20.0));
		productMap.put("P003", new Product("P003", "Product 3", "Description 3", 30.0));
		productMap.put("P004", new Product("P004", "Product 4", "Description 4", 56.0));
		productMap.put("P005", new Product("P005", "Product 5", "Description 5", 23.0));
		productMap.put("P006", new Product("P006", "Product 6", "Description 6", 15.0));
	}
    public Collection<Product> getAllProduct(){
		return productMap.values();
    }

	public Product getProductByCode(String code){
		return productMap.get(code);
	}
}
