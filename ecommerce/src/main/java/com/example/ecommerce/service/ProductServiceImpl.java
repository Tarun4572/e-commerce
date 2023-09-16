package com.example.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.Product;
import com.example.ecommerce.exceptions.IdNotFoundException;
import com.example.ecommerce.exceptions.UniqueNameException;
import com.example.ecommerce.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) throws UniqueNameException {

		// checking for unique name constraint
		Optional<Product> optional = productRepository.findByName(product.getName());

		if (optional.isPresent())
			throw new UniqueNameException("Product Name Should be unique"); // throwing uniqueNameException

		return productRepository.save(product);

	}

	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Optional<Product> getById(String productId) {
		return productRepository.findById(productId);
	}

	@Override
	public Product updateById(Product product, String productId) throws IdNotFoundException, UniqueNameException {
		boolean exists = productRepository.existsById(productId);

		if (!exists) // if Id doesn't exist throw Exception
			throw new IdNotFoundException("Product You wanted to update is not present!");

		Product existingProduct = productRepository.findById(productId).get();

		// if both are same, no need to save again in database
		if (existingProduct.equals(product))
			return existingProduct;

		// existingProduct and updatedProduct are not same, but if name is same, we can
		// save product.
		if (existingProduct.getName().equals(product.getName()))
			return productRepository.save(product);

		// if exists and new Product Name not found in DB, save the new Product.
		Optional<Product> optional = productRepository.findByName(product.getName());
		if (optional.isPresent())
			throw new UniqueNameException("Product Name Should be Unique!");
		else
			return productRepository.save(product);
	}

	@Override
	public boolean deleteById(String productId) throws IdNotFoundException {
		// checking whether product exists
		boolean exists = productRepository.existsById(productId);
		if (!exists)
			throw new IdNotFoundException("Product Id not found");

		productRepository.deleteById(productId);

		// checking again whether product is deleted or not.
		if (productRepository.existsById(productId))
			return false;
		else
			return true;
	}

}
