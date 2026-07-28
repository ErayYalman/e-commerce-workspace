package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.CreateProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.UpdateProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.PageResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.ProductResponse;

public interface IProductService {
    ProductResponse createProduct(CreateProductRequest productRequest);

    ProductResponse getProductById(UUID productId);

    PageResponse<ProductResponse> getAllProducts(Pageable pageable); //bu method, pageable parametresi ile birlikte tüm ürünleri sayfalı 
    // olarak döndürecek şekilde tasarlanmıştır.

    ProductResponse updateProduct(UUID productId, UpdateProductRequest productRequest);

    void deleteProduct(UUID productId);


}
