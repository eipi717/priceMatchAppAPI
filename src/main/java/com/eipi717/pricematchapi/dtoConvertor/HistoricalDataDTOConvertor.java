package com.eipi717.pricematchapi.dtoConvertor;

import com.eipi717.pricematchapi.dtos.HistoricalDataDTO;
import com.eipi717.pricematchapi.entity.HistoricalData;
import com.eipi717.pricematchapi.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HistoricalDataDTOConvertor {
    public static HistoricalDataDTO convert(HistoricalData historicalData) {
        return new HistoricalDataDTO.Builder()
                .setDataId(historicalData.getDataId())
                .setUnit(historicalData.getUnit())
                .setPricePerUnit(historicalData.getPricePerUnit())
                .setPrice(historicalData.getPrice())
                .setProductName(historicalData.getProduct().getProductName())
                .setStoreName(historicalData.getStore().getStoreName())
                .setCreatedTime(historicalData.getCreatedTime())
                .setDateOfPrice(historicalData.getDateOfPrice())
                .build();
    }
}
