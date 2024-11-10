package com.eipi717.pricematchapi.service;

import com.eipi717.pricematchapi.dtos.ProductDTO;
import com.eipi717.pricematchapi.entity.Product;
import com.eipi717.pricematchapi.repository.ProductRepository;
import com.eipi717.pricematchapi.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAllProduct(int page, int item, String sortBy, String orderBy) {
        Pageable pageable = PageRequest.of(page, item, Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));
        return productRepository.findAll(pageable);
    }

    public Product getProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public Product getProductById(Long productId) {
        return productRepository.findByProductId(productId);
    }

    public List<Product> getProductByCategory(int page, int size, String productCategory, String sortBy, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));
        return productRepository.findByProductCategory(productCategory, pageable);
    }

    public void createProduct(ProductDTO productDTO) {

        if (!productRepository.existsByProductName(productDTO.getProductName())) {
            Product product = new Product();

            product.setProductName(productDTO.getProductName());
            product.setProductCategory(productDTO.getProductCategory());
            product.setProductImage(productDTO.getProductImage());
            product.setCreatedTime(System.currentTimeMillis());
            product.setUpdatedTime(System.currentTimeMillis());

            productRepository.save(product);
            productRepository.flush();
        }
        Product product = productRepository.findByProductName(productDTO.getProductName());
        product.setProductImage(productDTO.getProductImage());
        product.setProductCategory(productDTO.getProductCategory());
        product.setUpdatedTime(System.currentTimeMillis());
        productRepository.save(product);
        productRepository.flush();
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
        productRepository.flush();
    }

    public List<Product> searchProduct(String query) {
        return productRepository.findByProductNameContaining(query);
    }
}
