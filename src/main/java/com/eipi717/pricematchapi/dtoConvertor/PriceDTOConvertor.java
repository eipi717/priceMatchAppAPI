package com.eipi717.pricematchapi.dtoConvertor;

import com.eipi717.pricematchapi.dtos.PriceDTO;
import com.eipi717.pricematchapi.entity.Price;
import com.eipi717.pricematchapi.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

@Service
public class PriceDTOConvertor {
    public static PriceDTO convert(Price price) {
        PriceDTO priceDTO = new PriceDTO();

        priceDTO.setPriceId(price.getPriceId());
        priceDTO.setProductCategory(price.getProduct().getProductCategory());
        priceDTO.setProductName(price.getProduct().getProductName());
        priceDTO.setStoreName(price.getStore().getStoreName());
        priceDTO.setPrice(price.getPrice());
        priceDTO.setSize(price.getSize());
        priceDTO.setPricePerUnit(price.getPricePerUnit());
        priceDTO.setUnit(price.getUnit());
        priceDTO.setStartDate(DateTimeUtils.tsToDate(price.getStartDate()));
        priceDTO.setUpdatedTime(DateTimeUtils.tsToDate(price.getUpdatedTime()));
        priceDTO.setEndDate(DateTimeUtils.tsToDate(price.getEndDate()));

        return priceDTO;
    }
}
