package com.nda.order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Component;

import com.nda.order.entity.Order;

@Component
public class DataContext {
	private final Map<Integer, Order> orders = new HashMap<>();

	public Collection<Order> getAllOrders() {
		return orders.values();
	}

	public Optional<Order> getOrderById(Integer code) {
		return Objects.isNull(code) ? Optional.empty() : Optional.ofNullable(orders.get(code));
	}


	public Order save(Order order) {
		final var orderId = (Integer) ObjectUtils.defaultIfNull(order.getId(),newId());
		order.setId(orderId);
		 orders.put(orderId, order);
		return order;
	}

	private Integer newId() {
		return  (1 + orders.keySet().stream().max(Integer::compareTo).orElse(0));
	}
}
