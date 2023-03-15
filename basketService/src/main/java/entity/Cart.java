package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.base.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
	private Integer id;
	@Default
	private final Set<CartLine> lines = new HashSet<>();

	public Cart addLine(CartLine cartLine) {
		cartLine.setCart(this);
		lines.add(cartLine);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Cart)) {
			return false;
		}
		Cart cart = (Cart) o;
		return Objects.equal(id, cart.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	public Order toOrder() {
		final var orderLines = lines.stream().map(Cart::getOrderLine).collect(Collectors.toSet());
		final var totalPrice = lines.stream().mapToDouble(CartLine::getTotalPrice).sum();
		return Order.builder().orderLines(orderLines).totalPrice(totalPrice).build();
	}

	private static OrderLine getOrderLine(CartLine cartLine) {
		final var product = cartLine.getProduct();
		return OrderLine.builder().price(product.getPrice()).quantity(cartLine.getQuantity()).productCode(product.getCode())
				.totalPrice(cartLine.getTotalPrice()).build();
	}

	@Override
	public String toString() {
		return "Cart{" +
				"id=" + id +
				", cartLineSet=" + lines +
				'}';
	}
}
