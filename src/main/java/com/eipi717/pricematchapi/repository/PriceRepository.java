package com.eipi717.pricematchapi.repository;

import com.eipi717.pricematchapi.entity.Price;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    public List<Price> findByProductProductId(Long productId, Sort sort);

    public List<Price> findByProductProductName(String productName, Sort sort);

    public List<Price> findByStoreStoreId(Long storeId, Sort sort);

    public List<Price> findByStoreStoreName(String storeName, Sort sort);

    public Price findByProductProductNameAndStoreStoreName(String productName, String storeName);

    public boolean existsByProductProductNameAndStoreStoreName(String productName, String storeName);
}
