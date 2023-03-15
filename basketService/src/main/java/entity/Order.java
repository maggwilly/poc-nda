package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
public class Order implements Serializable {
	private Double totalPrice;
	@Default
	private final Set<OrderLine> orderLines = new HashSet<>();
}
