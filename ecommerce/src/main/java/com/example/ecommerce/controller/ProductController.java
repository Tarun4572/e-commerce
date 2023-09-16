package com.example.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.Product;
import com.example.ecommerce.exceptions.IdNotFoundException;
import com.example.ecommerce.exceptions.UniqueNameException;
import com.example.ecommerce.payload.request.ProductRequest;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.utils.RequestToEntityMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private RequestToEntityMapper mapper;

	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest)
			throws UniqueNameException {

		Product product;
		product = mapper.getProductEntityObject(productRequest);
		Product createdProduct = productService.createProduct(product);
		return new ResponseEntity<Product>(createdProduct, HttpStatus.CREATED);
	}

	@GetMapping("/allProducts")
	public ResponseEntity<?> getAllProducts() {

		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable("productId") String productId) throws IdNotFoundException {

		Optional<Product> optional = productService.getById(productId);
		if (optional.isPresent())
			return ResponseEntity.ok(optional.get());
		else
			throw new IdNotFoundException("Product Id not Found!");
	}

	@PutMapping("/{productId}")
	public ResponseEntity<?> updateProductById(@PathVariable("productId") String productId,
			@Valid @RequestBody Product product) throws IdNotFoundException, UniqueNameException {

		Product updatedProduct = productService.updateById(product, productId);
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId) throws IdNotFoundException {

		productService.deleteById(productId);

		return ResponseEntity.noContent().build();
	}
}
