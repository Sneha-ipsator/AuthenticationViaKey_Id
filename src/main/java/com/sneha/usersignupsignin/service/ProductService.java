package com.sneha.usersignupsignin.service;

import com.sneha.usersignupsignin.entity.Products;
import com.sneha.usersignupsignin.payload.ServiceResponse;
import com.sneha.usersignupsignin.record.ProductRecord;

import java.util.List;

public interface ProductService {
    public ServiceResponse<String> addProduct(ProductRecord productRecord);
    public ServiceResponse<String> updateProduct(Integer productId, ProductRecord productRecord);
public List<Products> getProducts();
}
