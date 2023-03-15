package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartLine {
	private Product product;
	private Double totalPrice;
	private Integer quantity;
	@JsonIgnore
	private Cart cart;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CartLine)) {
			return false;
		}
		CartLine cartLine = (CartLine) o;
		return Objects.equal(product, cartLine.product) && Objects.equal(cart, cartLine.cart);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(product, cart);
	}

	@Override
	public String toString() {
		return "CartLine{" +
				"product=" + product +
				", totalPrice=" + totalPrice +
				", quantity=" + quantity+
				'}';
	}
}
