package com.eipi717.pricematchapi.service;

import com.eipi717.pricematchapi.dtos.HistoricalDataDTO;
import com.eipi717.pricematchapi.entity.HistoricalData;
import com.eipi717.pricematchapi.entity.Product;
import com.eipi717.pricematchapi.entity.Store;
import com.eipi717.pricematchapi.repository.HistoricalDataRepository;
import com.eipi717.pricematchapi.repository.ProductRepository;
import com.eipi717.pricematchapi.repository.StoreRepository;
import com.eipi717.pricematchapi.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricalPriceService {
    private final HistoricalDataRepository historicalDataRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public HistoricalPriceService(HistoricalDataRepository historicalDataRepository, ProductRepository productRepository, StoreRepository storeRepository) {
        this.historicalDataRepository = historicalDataRepository;
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public List<HistoricalData> findAllHistoricalData(String sortBy, String orderBy) {
        return historicalDataRepository.findAll(Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));
    }

    public List<HistoricalData> getByProductId(Long productId, String sortBy, String orderBy) {
        return historicalDataRepository.findByProductProductId(productId, Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));
    }

    public List<HistoricalData> getByProductName(String productName, String sortBy, String orderBy) {
        return historicalDataRepository.findByProductProductName(productName, Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));
    }

    public List<HistoricalData> getByStoreId(Long storeId, String sortBy, String orderBy) {
        return historicalDataRepository.findByStoreStoreId(storeId, Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));
    }

    public List<HistoricalData> getByStoreName(String storeName, String sortBy, String orderBy) {
        return historicalDataRepository.findByStoreStoreName(storeName, Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));
    }

    public void createHistoricalData(HistoricalDataDTO historicalDataDTO) {
        HistoricalData historicalData = new HistoricalData();
        Product product = productRepository.findByProductName(historicalDataDTO.getProductName());
        Store store = storeRepository.findByStoreName(historicalDataDTO.getStoreName());

        historicalData.setProduct(product);
        historicalData.setStore(store);
        historicalData.setPricePerUnit(historicalDataDTO.getPricePerUnit());
        historicalData.setPrice(historicalDataDTO.getPrice());
        historicalData.setUnit(historicalDataDTO.getUnit());
        historicalData.setDateOfPrice(historicalDataDTO.getDateOfPrice());
        historicalData.setCreatedTime(System.currentTimeMillis());

        historicalDataRepository.save(historicalData);
        historicalDataRepository.flush();
    }
}
