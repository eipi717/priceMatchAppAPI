package com.eipi717.pricematchapi.service;

import com.eipi717.pricematchapi.dtos.PriceDTO;
import com.eipi717.pricematchapi.entity.Price;
import com.eipi717.pricematchapi.entity.Product;
import com.eipi717.pricematchapi.entity.Store;
import com.eipi717.pricematchapi.repository.PriceRepository;
import com.eipi717.pricematchapi.repository.ProductRepository;
import com.eipi717.pricematchapi.repository.StoreRepository;
import com.eipi717.pricematchapi.utils.DateTimeUtils;
import com.eipi717.pricematchapi.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for handling business logic related to prices.
 */
@Service
public class PriceService {
    private final PriceRepository priceRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    /**
     * Constructs a PriceService with required repositories.
     *
     * @param priceRepository   the repository for price entities
     * @param storeRepository   the repository for store entities
     * @param productRepository the repository for product entities
     */
    @Autowired
    public PriceService(PriceRepository priceRepository, StoreRepository storeRepository, ProductRepository productRepository) {
        this.priceRepository = priceRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all prices, sorted according to the provided parameters.
     *
     * @param sortBy  the property to sort by
     * @param orderBy the direction to sort (asc/desc)
     * @return a list of sorted Price entities
     */
    public List<Price> getAllPrice(String sortBy, String orderBy) {
        return priceRepository.findAll(Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));
    }

    /**
     * Retrieves prices for a specific product ID, sorted according to the provided parameters.
     *
     * @param productId the ID of the product
     * @return a Price entities
     */
    public List<Price> getByProductId(Long productId) {
        return priceRepository.findByProductProductId(productId);
    }

    /**
     * Retrieves prices for a specific product name, sorted according to the provided parameters.
     *
     * @param productName the name of the product
     * @return a Price entities
     */
    public List<Price> getByProductName(String productName) {
        return priceRepository.findByProductProductName(productName);
    }

    /**
     * Retrieves prices for a specific store ID, sorted according to the provided parameters.
     *
     * @param storeId the ID of the store
     * @param sortBy  the property to sort by
     * @param orderBy the direction to sort (asc/desc)
     * @return a list of sorted Price entities
     */
    public List<Price> getByStoreId(Long storeId, String sortBy, String orderBy) {
        return priceRepository.findByStoreStoreId(storeId, Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));
    }

    /**
     * Retrieves prices for a specific store name, sorted according to the provided parameters.
     *
     * @param storeName the name of the store
     * @param sortBy    the property to sort by
     * @param orderBy   the direction to sort (asc/desc)
     * @return a list of sorted Price entities
     */
    public List<Price> getByStoreName(String storeName, String sortBy, String orderBy) {
        return priceRepository.findByStoreStoreName(storeName, Sort.by(QueryUtils.getSortingDirection(orderBy), sortBy));
    }

    /**
     * Inserts a new price into the repository based on the provided PriceDTO.
     *
     * @param priceDTO the Data Transfer Object containing the new price details
     */
    public void createPrice(PriceDTO priceDTO) {
        if (priceRepository.existsByProductProductNameAndStoreStoreName(priceDTO.getProductName(), priceDTO.getStoreName())) {
            Price price = priceRepository.findByProductProductNameAndStoreStoreName(priceDTO.getProductName(), priceDTO.getStoreName());
            price.setPrice(priceDTO.getPrice());
            price.setSize(priceDTO.getSize());
            price.setPricePerUnit(priceDTO.getPricePerUnit());
            price.setStartDate(DateTimeUtils.DateToTs(priceDTO.getStartDate()));
            price.setEndDate(DateTimeUtils.DateToTs(priceDTO.getEndDate()));
            price.setUpdatedTime(System.currentTimeMillis());

            priceRepository.save(price);
            priceRepository.flush();
        }
        else {
            Price price = new Price();
            Product product = productRepository.findByProductName(priceDTO.getProductName());
            Store store = storeRepository.findByStoreName(priceDTO.getStoreName());

            price.setProduct(product);
            price.setStore(store);
            price.setPricePerUnit(priceDTO.getPricePerUnit());
            price.setPrice(priceDTO.getPrice());
            price.setUnit(priceDTO.getUnit());
            price.setSize(priceDTO.getSize());
            price.setStartDate(DateTimeUtils.DateToTs(priceDTO.getStartDate()));
            price.setCreatedTime(System.currentTimeMillis());
            price.setUpdatedTime(System.currentTimeMillis());
            price.setEndDate(DateTimeUtils.DateToTs(priceDTO.getEndDate()));

            priceRepository.save(price);
            priceRepository.flush();
        }
    }

    public void deletePriceById(Long priceId) {
        priceRepository.deleteById(priceId);
        priceRepository.flush();
    }

}
