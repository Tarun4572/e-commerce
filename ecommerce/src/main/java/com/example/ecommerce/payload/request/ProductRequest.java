package com.example.ecommerce.payload.request;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

	@NotBlank(message = "Product Cannot be blank")
	@Column(unique = true)
	private String name;

	@NotNull(message = "Price cannot be Null")
	@Min(value = 0, message = "Price cannot be negative")
	private float price;

	@NotBlank(message = "Description cannot be null")
	private String description;
	private String category;
	private LocalDate expirationDate;
}
