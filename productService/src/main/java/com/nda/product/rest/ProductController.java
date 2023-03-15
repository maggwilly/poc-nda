package com.nda.product.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nda.product.entity.DataContext;
import com.nda.product.entity.Product;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Resource
	private DataContext dataContext;

	@GetMapping
	public List<Product> getAllProducts() {
		return new ArrayList<>(dataContext.getAllProduct());
	}

	@GetMapping("/{code}")
	public Product getProductByCode(@PathVariable String code) {
		return dataContext.getProductByCode(code);
	}
}
