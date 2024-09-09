package com.example.demo.controller;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.service.serviceImpl.ProductServiceImpl;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing products.
 */
@RestController
public class ProductController {
    private final ProductServiceImpl productService;
    private final Logger logger;

    /**
     * Constructs a new ProductController with the specified ProductServiceImpl and Logger.
     *
     * @param productService the service for managing products
     * @param logger the logger instance
     */
    public ProductController(ProductServiceImpl productService, Logger logger) {
        this.productService = productService;
        this.logger = logger;
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return a ResponseEntity containing the ProductDto and HTTP status
     * @throws ProductException if the product with the specified ID is not found
     */
    @GetMapping("getById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String id) {
        try {
            Product product = productService.findById(id);
            ProductDto productDto = productService.mapToProductDto(product);
            return ResponseEntity.ok(productDto);
        } catch (ProductException e) {
            logger.error("Product not found with ID: " + id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Adds a new product.
     *
     * @param productDto the ProductDto to add
     * @return a ResponseEntity containing the added ProductDto and HTTP status
     * @throws ProductException if there is an issue with adding the product (e.g., validation failure)
     */
    @PostMapping("addProduct")
    public ResponseEntity<?> addNewProduct(@RequestBody ProductDto productDto) {
        try {
            Product product = productService.mapToProduct(productDto);
            ProductDto savedProductDto = productService.mapToProductDto(productService.addProduct(product));
            return new ResponseEntity<>(savedProductDto, HttpStatus.OK);
        } catch (ProductException e) {
            logger.error("Error adding product: " + productDto, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
