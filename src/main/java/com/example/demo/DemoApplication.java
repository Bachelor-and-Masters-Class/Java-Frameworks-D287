package com.example.demo;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadSampleData(ProductService productService) {
		return args -> {
			if (productService.listAll(null).isEmpty()) {
				productService.save(new Product("Cordless Drill Kit", 59.99, 10));
				productService.save(new Product("Electrician Tool Kit", 89.99, 5));
				productService.save(new Product("Plumbing Repair Kit", 74.99, 8));
				productService.save(new Product("General Repair Set", 49.99, 12));
				productService.save(new Product("Deluxe Home Repair Kit", 109.99, 6));
			}
		};
	}
}
