package com.eipi717.pricematchapi.repository;

import com.eipi717.pricematchapi.entity.Price;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByProductProductId(Long productId);

    List<Price> findByProductProductName(String productName);

    List<Price> findByStoreStoreId(Long storeId, Sort sort);

    List<Price> findByStoreStoreName(String storeName, Sort sort);

    Price findByProductProductNameAndStoreStoreName(String productName, String storeName);

    boolean existsByProductProductNameAndStoreStoreName(String productName, String storeName);
}
