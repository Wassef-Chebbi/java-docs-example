package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a product in the system.
 */
@Document("products")
public class Product {
    @Id
    private String id;
    private String nameProduct;
    private float priceProduct;

    /**
     * Constructs a new Product with the specified details.
     *
     * @param id the unique identifier for this product
     * @param nameProduct the name of the product
     * @param priceProduct the price of the product
     */
    public Product(String id, String nameProduct, float priceProduct) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
    }

    /**
     * Gets the unique identifier of the product.
     *
     * @return the unique identifier of the product
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the product.
     *
     * @param id the new unique identifier for the product
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the product.
     *
     * @return the name of the product
     */
    public String getNameProduct() {
        return nameProduct;
    }

    /**
     * Sets the name of the product.
     *
     * @param nameProduct the new name for the product
     */
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    /**
     * Gets the price of the product.
     *
     * @return the price of the product
     */
    public float getPriceProduct() {
        return priceProduct;
    }

    /**
     * Sets the price of the product.
     *
     * @param priceProduct the new price for the product
     */
    public void setPriceProduct(float priceProduct) {
        this.priceProduct = priceProduct;
    }
}
