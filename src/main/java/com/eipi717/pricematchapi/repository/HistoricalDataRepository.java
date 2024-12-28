package com.eipi717.pricematchapi.repository;

import com.eipi717.pricematchapi.entity.HistoricalData;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricalDataRepository extends JpaRepository<HistoricalData, Long> {
    public List<HistoricalData> findByProductProductId(Long productId, Sort sort);

    public List<HistoricalData> findByProductProductName(String productName, Sort sort);

    public List<HistoricalData> findByStoreStoreId(Long storeId, Sort sort);

    public List<HistoricalData> findByStoreStoreName(String storeName, Sort sort);

    public HistoricalData findByProductProductNameAndStoreStoreName(String productName, String storeName);

    public boolean existsByProductProductNameAndStoreStoreName(String productName, String storeName);
}
