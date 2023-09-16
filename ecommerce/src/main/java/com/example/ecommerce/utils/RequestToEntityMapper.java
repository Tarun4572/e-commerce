package com.example.ecommerce.utils;

import org.springframework.stereotype.Component;

import com.example.ecommerce.dto.Product;
import com.example.ecommerce.payload.request.ProductRequest;

@Component
public class RequestToEntityMapper {

	public Product getProductEntityObject(ProductRequest productRequest) {

		Product product = new Product();

		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setCategory(productRequest.getCategory());
		product.setPrice(productRequest.getPrice());
		product.setExpirationDate(productRequest.getExpirationDate());

		return product;
	}
}