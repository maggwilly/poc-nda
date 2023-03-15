package com.nda.order.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
	private Integer id;
	private Double totalPrice;
	@Default
	private Status status = Status.CREATED;
	@Default
	private final Set<OrderLine> orderLines = new HashSet<>();
}
