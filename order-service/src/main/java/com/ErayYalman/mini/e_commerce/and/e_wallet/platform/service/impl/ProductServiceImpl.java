package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.CreateProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.UpdateProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.PageResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.ProductResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.Product;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.exception.ProductNotFoundException;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.mapper.CreateProductMapper;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.repository.ProductRepository;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.IProductService;




@Service
@Transactional //Transactional ekledim çünkü bu sınıfın metotları veritabanı işlemleri yapacak ve bu işlemler bir bütün olarak ele alınmalı. Eğer bir işlem başarısız olursa, tüm işlemler geri alınmalı.
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final CreateProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, CreateProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    } //veya sınıfın üstüne @RequiredArgsConstructor ekleyebilirsin ve constructor yazmana gerek kalmaz.

    @Override
    @Transactional
    public ProductResponse createProduct(CreateProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        Product savedProduct = productRepository.save(product);
        ProductResponse response = productMapper.toResponse(savedProduct);
        return response; 

    }

    @Override
    public ProductResponse getProductById(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        return productMapper.toResponse(product);

    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> getAllProducts(Pageable pageable) {
        Page<ProductResponse> productPage = productRepository.findAll(pageable)
                .map(productMapper::toResponse);
        return PageResponse.<ProductResponse>builder() //PageResponse sınıfının builder metodunu kullanarak bir PageResponse nesnesi 
        // oluşturuyoruz. Bu nesne, sayfalama bilgilerini ve ürün listesini içeriyor.
                .content(productPage.getContent())
                .page(productPage.getNumber())
                .size(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .first(productPage.isFirst())
                .last(productPage.isLast())
                .build();
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(UUID productId, UpdateProductRequest productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        productMapper.updateProductFromRequest(productRequest, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toResponse(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        productRepository.delete(product);
    }

   
    
}
