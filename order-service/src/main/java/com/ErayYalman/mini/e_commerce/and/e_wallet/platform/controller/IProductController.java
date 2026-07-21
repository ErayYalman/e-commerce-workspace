package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller;

import java.util.List;
import java.util.UUID;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.CreateProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.UpdateProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.ProductResponse;

public interface IProductController {
    ProductResponse createProduct(CreateProductRequest productRequest);

    ProductResponse getProductById(UUID productId);

    List<ProductResponse> getAllProducts(); 

    ProductResponse updateProduct(UUID productId, UpdateProductRequest productRequest);
    
    void deleteProduct(UUID productId);


        
}
