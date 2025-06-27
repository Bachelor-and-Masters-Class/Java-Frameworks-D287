package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int theId) {
        return productRepository.findById((long) theId)
                .orElseThrow(() -> new RuntimeException("Did not find product id - " + theId));
    }

    @Override
    public void save(Product theProduct) {
        productRepository.save(theProduct);
    }

    @Override
    public void deleteById(int theId) {
        productRepository.deleteById((long) theId);
    }

    @Override
    public List<Product> listAll(String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        save(product);
    }
}
