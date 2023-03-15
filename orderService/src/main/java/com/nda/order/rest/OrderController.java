package com.nda.order.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nda.order.DataContext;
import com.nda.order.entity.Order;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	@Resource
	private DataContext context;

	@GetMapping
	public List<Order> getAllProducts() {
		return new ArrayList<>(context.getAllOrders());
	}
	@PostMapping
	public ResponseEntity<Order> create(@RequestBody Order order) {
		return ResponseEntity.accepted().body(context.save(order));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Order> update(@PathVariable Integer id, @RequestBody Order order) {
		final var elseThrow = context.getOrderById(id).orElseThrow(NoSuchElementException::new);
		elseThrow.setStatus(order.getStatus());
		context.save(elseThrow);
		return ResponseEntity.ok().body(elseThrow);
	}
}
