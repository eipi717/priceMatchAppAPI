package com.eipi717.pricematchapi.service;

import com.eipi717.pricematchapi.dtoConvertor.StoreDTOConvertor;
import com.eipi717.pricematchapi.dtos.StoreDTO;
import com.eipi717.pricematchapi.entity.Store;
import com.eipi717.pricematchapi.repository.StoreRepository;
import com.eipi717.pricematchapi.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> findAllStore(String sortBy, String orderBy) {

        return storeRepository.findAll(Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));

    }

    public StoreDTO findByStoreName(String storeName) {
        return StoreDTOConvertor.convert(storeRepository.findByStoreName(storeName));
    }

    public void createStore(StoreDTO storeDTO) {

        Store store = new Store();
        store.setStoreName(storeDTO.getStoreName());
        store.setCreatedTime(System.currentTimeMillis());

        storeRepository.save(store);
        storeRepository.flush();
    }

    public void deleteStore(Long storeId) {

        Store store = storeRepository.findByStoreId(storeId);

        storeRepository.delete(store);

        storeRepository.flush();
    }
}
