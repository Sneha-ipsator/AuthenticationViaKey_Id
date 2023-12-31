package com.sneha.usersignupsignin.controller;

import com.sneha.usersignupsignin.entity.Products;
import com.sneha.usersignupsignin.payload.ApiResponse;
import com.sneha.usersignupsignin.payload.Error;
import com.sneha.usersignupsignin.payload.ServiceResponse;
import com.sneha.usersignupsignin.record.ProductRecord;
import com.sneha.usersignupsignin.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class defines the REST endpoints for product-related operations.
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productServiceImpl;

    /**
     * Add a new product.
     *
     * @param productRecord The product record to be added.
     * @return ResponseEntity indicating the success or failure of the operation.
     */
    @PostMapping("/addProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductRecord productRecord)
    {
        try{
            ServiceResponse<String>response=productServiceImpl.addProduct(productRecord);
            if(response.getSuccess()) {
                return new ResponseEntity<>(new ApiResponse("sucess", response.getData(), null), HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(new ApiResponse("error", null, new Error(response.getMessage())),
                        HttpStatus.CONFLICT); // Use HTTP status code 409 for conflict
            }
        }
        catch(Exception e)
        {
             return new ResponseEntity<>(new ApiResponse("error",null,new Error(e.getMessage())),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Edit an existing product.
     *
     * @param productId    The ID of the product to be edited.
     * @param productRecord The updated product record.
     * @return ResponseEntity indicating the success or failure of the operation.
     */
    @PutMapping("/editProduct")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<ApiResponse> editProduct(@RequestParam(name = "productId") Integer productId,@RequestBody ProductRecord productRecord) {
        try {
            ServiceResponse<String> response = productServiceImpl.updateProduct(productId, productRecord);
            return new ResponseEntity<>(new ApiResponse("success", "updated Successfully", null), HttpStatus.OK);
        }

        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("error","An error occurred",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get a list of products.
     *
     * @return ResponseEntity containing a list of products or an error response.
     */

    @GetMapping("/getProducts")
    public ResponseEntity<List<Products>> getProducts() {
        try{
            List<Products> products = productServiceImpl.getProducts();
            System.out.println(products);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
