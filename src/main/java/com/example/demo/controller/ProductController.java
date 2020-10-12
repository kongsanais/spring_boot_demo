package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.model.Product;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final AtomicLong counter = new AtomicLong();
	private List<Product> products = new ArrayList<>();

	// .../say
	@GetMapping()
	public List<Product> getProducts() {
		return products;
	}

	// .../say/1234
	@GetMapping("/{id}")
	public Product getProductsById(@PathVariable long id) {
		return products.stream().filter(result -> result.getId() == id).findFirst()
				.orElseThrow(() -> new ProductNotFoundException(id));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public Product addProduct(@RequestBody Product product) {
		Product data = new Product(counter.incrementAndGet(), product.getName(), product.getImage(), product.getPrice(),
				product.getStock());
		products.add(data);
		return data;
	}

	@PutMapping("/{id}")
	public void editProduct(@RequestBody Product product, @PathVariable long id) {
		products.stream().filter(result -> result.getId() == id).findFirst().ifPresentOrElse(result -> {
			result.setName(product.getName());
			result.setImage(product.getImage());
			result.setPrice(product.getPrice());
			result.setStock(product.getStock());
		}, () -> {
			throw new ProductNotFoundException(id);
		});
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable long id) {
		products.stream().filter(result -> result.getId() == id).findFirst().ifPresentOrElse(result -> {
			products.remove(result);
		}, () -> {
			throw new ProductNotFoundException(id);
		});

	}

}
