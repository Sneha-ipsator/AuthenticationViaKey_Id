package com.sneha.usersignupsignin.service;

import com.sneha.usersignupsignin.entity.Products;
import com.sneha.usersignupsignin.payload.ServiceResponse;
import com.sneha.usersignupsignin.record.ProductRecord;

import java.util.List;

/**
 * Service interface for managing products.
 */
public interface ProductService {

    /**
     * Add a new product.
     *
     * @param productRecord The record containing product information to add.
     * @return A service response indicating the success or failure of the operation.
     */
    public ServiceResponse<String> addProduct(ProductRecord productRecord);

    /**
     * Update an existing product.
     *
     * @param productId The ID of the product to update.
     * @param productRecord The record containing updated product information.
     * @return A service response indicating the success or failure of the operation.
     */
    public ServiceResponse<String> updateProduct(Integer productId, ProductRecord productRecord);

    /**
     * Get a list of all products.
     *
     * @return A list of products.
     */
     public List<Products> getProducts();
}
