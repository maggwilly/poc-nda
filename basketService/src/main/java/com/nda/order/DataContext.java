package com.nda.order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Component;

import entity.Cart;
import entity.CartLine;

@Component
public class DataContext {
	private final Map<Integer, Cart> carts = new HashMap<>();

	public Collection<Cart> getAllCarts() {
		return carts.values();
	}

	public Optional<Cart> getCartById(Integer code) {
		final var optionalCart = Optional.ofNullable(carts.get(code));
		return Objects.isNull(code) ? Optional.empty() : optionalCart;
	}

	public Cart addToCart(Cart cart, CartLine cartLine) {
		final var added = cart.addLine(cartLine);
		return save(added);
	}

	private Cart save(Cart cart) {
		final var orderId = (Integer) ObjectUtils.defaultIfNull(cart.getId(),newId());
		cart.setId(orderId);
		carts.put(cart.getId(), cart);
		return cart;
	}

	private Integer newId() {
		return  (1 + carts.keySet().stream().max(Integer::compareTo).orElse(0));
	}
}
