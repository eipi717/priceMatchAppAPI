package com.eipi717.pricematchapi.service;

import com.eipi717.pricematchapi.dtos.ProductDTO;
import com.eipi717.pricematchapi.entity.Product;
import com.eipi717.pricematchapi.repository.ProductRepository;
import com.eipi717.pricematchapi.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Product> getAllProduct(String sortBy, String orderBy) {
        return productRepository.findAll(Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));
    }

    public Product getProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public Product getProductById(Long productId) {
        return productRepository.findByProductId(productId);
    }

    public List<Product> getProductByCategory(String productCategory, String sortBy, String orderBy) {

        return productRepository.findByProductCategory(productCategory, Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));
    }

    public void createProduct(ProductDTO productDTO) {

        if (!productRepository.existsByProductName(productDTO.productName())) {
            Product product = new Product();

            product.setProductName(productDTO.productName());
            product.setProductCategory(productDTO.productCategory());
            product.setCreatedTime(System.currentTimeMillis());
            product.setUpdatedTime(System.currentTimeMillis());

            productRepository.save(product);
            productRepository.flush();
        }
        Product product = productRepository.findByProductName(productDTO.productName());
        product.setProductCategory(productDTO.productCategory());
        product.setUpdatedTime(System.currentTimeMillis());
        productRepository.save(product);
        productRepository.flush();
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
        productRepository.flush();
    }
}
