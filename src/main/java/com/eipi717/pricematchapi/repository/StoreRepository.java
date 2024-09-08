package com.eipi717.pricematchapi.repository;

import com.eipi717.pricematchapi.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByStoreName(String storeName);

    Store findByStoreId(Long storeId);
}
