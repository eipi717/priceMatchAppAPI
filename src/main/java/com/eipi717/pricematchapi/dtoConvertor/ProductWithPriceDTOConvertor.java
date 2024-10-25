package com.eipi717.pricematchapi.dtoConvertor;

import com.eipi717.pricematchapi.dtos.ProductWithPriceDTO;
import com.eipi717.pricematchapi.entity.Product;
import com.eipi717.pricematchapi.utils.DateTimeUtils;

public class ProductWithPriceDTOConvertor {
    public static ProductWithPriceDTO convert(Product product, Double price) {
        ProductWithPriceDTO productWithPriceDTO = new ProductWithPriceDTO();
        productWithPriceDTO.setProductId(product.getProductId());
        productWithPriceDTO.setProductCategory(product.getProductCategory());
        productWithPriceDTO.setProductName(product.getProductName());
        productWithPriceDTO.setProductImage(product.getProductImage());
        productWithPriceDTO.setProductCreatedTime(DateTimeUtils.tsToDate(product.getCreatedTime()));
        productWithPriceDTO.setProductUpdatedTime(DateTimeUtils.tsToDate(product.getUpdatedTime()));
        productWithPriceDTO.setCurrentPrice(price);

        return productWithPriceDTO;
    }
}
