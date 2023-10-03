package com.sneha.usersignupsignin.service;

import com.sneha.usersignupsignin.entity.Products;
import com.sneha.usersignupsignin.payload.ServiceResponse;
import com.sneha.usersignupsignin.record.ProductRecord;
import com.sneha.usersignupsignin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing products.
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    /**
     * Add a new product.
     *
     * @param productRecord The record containing product information to add.
     * @return A service response indicating the success or failure of the operation.
     */
    @Override
    public ServiceResponse<String> addProduct(ProductRecord productRecord) {
        Optional<Products> existingProductOpt = productRepository.findByPname(productRecord.pname());
        if(existingProductOpt.isPresent()) {
            ServiceResponse<String> response = new ServiceResponse<>(false, null,
                    "Product exists.Try to add another one.");
            return response;
        }
        Products product=new Products();
        product.setPname(productRecord.pname());
        product.setDescription(productRecord.description());
        product.setQuantity(productRecord.quantity());
        product.setPrice(productRecord.price());
        Products saveProduct=productRepository.save(product);
        ServiceResponse<String> response=new ServiceResponse<>(true, saveProduct.getPname()+" added succesfuly", "Thank you");
        return  response;
    }

    /**
     * Get a list of all products.
     *
     * @return A list of products.
     */
    @Override
    public List<Products> getProducts() {
        return productRepository.findAll();
    }

    /**
     * Update an existing product.
     *
     * @param productId The ID of the product to update.
     * @param productRecord The record containing updated product information.
     * @return A service response indicating the success or failure of the operation.
     */
    @Override
    public ServiceResponse<String> updateProduct(Integer productId, ProductRecord productRecord) {
        try{
           Optional<Products> optionalProduct=productRepository.findById(productId);


            if (optionalProduct.isPresent()) {
                Products existingProduct = optionalProduct.get();

                existingProduct.setPname(productRecord.pname());
                existingProduct.setDescription(productRecord.description());
                existingProduct.setQuantity(productRecord.quantity());
                existingProduct.setPrice(productRecord.price());

                productRepository.save(existingProduct);

                ServiceResponse<String> response=new ServiceResponse<>(true, "Product updated successfully", "Thankyou");
                return response;
            } else {
                ServiceResponse<String> response=new ServiceResponse<>(false, "Product not found", "null");
                return response;
            }
        } catch (Exception e) {
            ServiceResponse<String> response=new ServiceResponse<>(false, "An error occurred: " + e.getMessage(), "null");
            return response;
        }
    }
}
