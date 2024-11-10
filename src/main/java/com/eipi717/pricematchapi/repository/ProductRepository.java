package com.eipi717.pricematchapi.repository;

import com.eipi717.pricematchapi.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    public Product findByProductName(String productName);

    public Product findByProductId(Long productId);

    public List<Product> findByProductCategory(String productCategory, Pageable pageable);

    public boolean existsByProductName(String productName);

    public List<Product> findByProductNameContaining(String query);
}
