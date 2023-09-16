package com.example.ecommerce.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.dto.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

	// select * from product where name = '?'
	public Optional<Product> findByName(String name);
}
