package com.RESTful.Api.services;

import com.RESTful.Api.model.Product;
import com.RESTful.Api.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return Optional.of(productRepo.findById(productId).get());
    }

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(Long productId, Product productDetails) {
        Product product = productRepo.findById(productId).orElseThrow();
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription());
        return productRepo.save(product);
    }

    public void deleteProduct(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow();
        productRepo.delete(product);
    }
}
