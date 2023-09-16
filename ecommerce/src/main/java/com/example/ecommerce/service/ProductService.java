package com.example.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.example.ecommerce.dto.Product;
import com.example.ecommerce.exceptions.IdNotFoundException;
import com.example.ecommerce.exceptions.UniqueNameException;

public interface ProductService {

	public Product createProduct(Product product) throws UniqueNameException;

	public List<Product> getAllProducts();

	public Optional<Product> getById(String productId);

	public boolean deleteById(String productId) throws IdNotFoundException;

	public Product updateById(Product product, String productId) throws IdNotFoundException, UniqueNameException;
}
