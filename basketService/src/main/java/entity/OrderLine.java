package entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine implements Serializable {
	private String productCode;
	private Integer quantity;
	private Double totalPrice;
	private Double price;
}
