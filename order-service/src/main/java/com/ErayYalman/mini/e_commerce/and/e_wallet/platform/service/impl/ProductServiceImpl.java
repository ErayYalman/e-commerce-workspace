package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.ProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.ProductResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.Product;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.mapper.ProductMapper;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.repository.ProductRepository;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.IProductService;

import jakarta.transaction.Transactional;


@Service
@Transactional //Transactional ekledim çünkü bu sınıfın metotları veritabanı işlemleri yapacak ve bu işlemler bir bütün olarak ele alınmalı. Eğer bir işlem başarısız olursa, tüm işlemler geri alınmalı.
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    } //veya sınıfın üstüne @RequiredArgsConstructor ekleyebilirsin ve constructor yazmana gerek kalmaz.

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        Product savedProduct = productRepository.save(product);
        ProductResponse response = productMapper.toResponse(savedProduct);
        return response; 

    }

    @Override
    public ProductResponse getProductById(UUID productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
    }

    @Override
    public ProductResponse updateProduct(UUID productId, ProductRequest productRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public void deleteProduct(UUID productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }
    
}
