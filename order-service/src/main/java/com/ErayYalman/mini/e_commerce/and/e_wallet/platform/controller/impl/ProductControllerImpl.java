package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller.impl;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.controller.IProductController;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.CreateProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.request.UpdateProductRequest;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.PageResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.dto.response.ProductResponse;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.service.impl.ProductServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductControllerImpl implements IProductController {

    private final ProductServiceImpl productService;

    public ProductControllerImpl(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Override
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody @Valid CreateProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @Override
    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable UUID productId) {
        return productService.getProductById(productId);
    }

    @Override
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ProductResponse> getAllProducts(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @Override
    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProduct(@PathVariable UUID productId, @RequestBody @Valid UpdateProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest);
        
    }

    @Override
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
    }

    
    
    
}
