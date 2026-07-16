package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service;

import java.util.List;
import java.util.UUID;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.ProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.ProductResponse;

public interface IProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse getProductById(UUID productId);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(UUID productId, ProductRequest productRequest);

    void deleteProduct(UUID productId);


}
