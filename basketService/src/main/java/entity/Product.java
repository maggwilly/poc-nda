package entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
	private String code;
	private String name;
	private String description;
	private Double price;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Product)) {
			return false;
		}
		Product product = (Product) o;
		return Objects.equal(code, product.code);
	}

	@Override
	public String toString() {
		return "Product{" +
				"code='" + code + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				'}';
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(code);
	}
}
