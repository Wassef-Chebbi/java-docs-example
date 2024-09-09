package com.example.demo.service.serviceImpl;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.repository.ProductRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl {
    private final ProductRepo productRepo;
    private final Logger logger;

    public ProductServiceImpl(ProductRepo productRepo, Logger logger) {
        this.productRepo = productRepo;
        this.logger = logger;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public ProductDto mapToProductDto(Product product) {
        try {
            return modelMapper().map(product, ProductDto.class);
        } catch (Exception e) {
            logger.error("Error mapping Product to ProductDto: ", e);
            throw new ProductException("Error mapping Product to ProductDto: " + e.getMessage());
        }
    }

    public Product mapToProduct(ProductDto productDto) {
        try {
            return modelMapper().map(productDto, Product.class);
        } catch (Exception e) {
            logger.error("Error mapping ProductDto to Product: ", e);
            throw new ProductException("Error mapping Product to ProductDto: " + e.getMessage());
        }
    }

    public List<Product> findAll() {
        try {
            return productRepo.findAll();
        } catch (Exception e) {
            logger.error("Error finding the list of products: ", e);
            throw new ProductException("Error finding the list of products: " + e.getMessage());
        }
    }

    public Product findById(String id) {
        return productRepo.findById(id).orElseThrow(() -> new ProductException("product not found !!"));
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }
}
