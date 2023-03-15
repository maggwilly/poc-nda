package com.nda.order.rest;

import java.util.NoSuchElementException;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import entity.Cart;
import entity.CartLine;
import com.nda.order.DataContext;
import entity.Order;
import entity.Product;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasketController {
	@Resource
	private RestTemplate restTemplate;
	@Resource
	private DataContext context;
	@Value("${product-service.host}")
	private String productServiceHost;
	@Value("${product-service.port}")
	private int productServicePort;
	@Value("${order-service.host}")
	private String orderServiceHost;
	@Value("${order-service.port}")
	private int orderServicePort;
	@PostMapping("/add/{code}")
	public Cart addToCart(@RequestParam(name = "cartId", required = false, defaultValue = "0") Integer id, @PathVariable String code, @RequestParam(name = "quantity", required = false, defaultValue = "1") Integer quantity) {
		String productUrl = String.format("%s:%d/products/%s", productServiceHost,productServicePort, code);
		ResponseEntity<Product> response = restTemplate.getForEntity(productUrl, Product.class);
		if (response.getStatusCode() == HttpStatus.NOT_FOUND || !response.hasBody()) {
			throw new NoSuchElementException();
		}
		final var product = response.getBody();

		log.info("Response {}",product);

		final var price = Objects.requireNonNull(product).getPrice();
		final var totalPrice = getTotalPrice(quantity, price);
		final var cartLine = CartLine.builder().quantity(quantity).totalPrice(totalPrice).product(product).build();
		final var cart = context.getCartById(id).orElse(Cart.builder().build());
		final var addToCart = context.addToCart(cart, cartLine);
		log.info("{}, added to cart {}",cart, addToCart);
		return addToCart;
	}

	private static double getTotalPrice(Integer quantity, Double price) {
		log.info("Price {} * quantity {}",price, quantity);
		return price * quantity;
	}

	@PostMapping("/place-order/{id}")
	public ResponseEntity<Object> placeOrder(@PathVariable Integer id) {
		String orderUrl = String.format("%s:%d/orders", orderServiceHost,orderServicePort);
		final var cart = context.getCartById(id).orElseThrow(NoSuchElementException::new);
		Order newOrder = cart.toOrder();
		final var response = restTemplate.postForEntity(orderUrl, newOrder, Object.class);
		log.info("placed order {}", response);
		if (response.getStatusCode() == HttpStatus.ACCEPTED && response.hasBody()) {
			final var responseBody = response.getBody();
			return ResponseEntity.accepted().body(responseBody);
		}
		return ResponseEntity.badRequest().body("Failed to place the order");
	}
}
