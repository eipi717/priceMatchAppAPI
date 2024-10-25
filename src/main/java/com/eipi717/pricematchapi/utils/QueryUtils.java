package com.eipi717.pricematchapi.utils;

import com.eipi717.pricematchapi.entity.Price;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class QueryUtils {
    public static Sort.Direction getSortingDirection(String orderBy) {
        if (orderBy.equalsIgnoreCase("DESC")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    public static Double getLatestPrice(List<Price> priceList) {
        return priceList
                .stream()
                .max(Comparator.comparingLong(Price::getUpdatedTime))
                .map(Price::getPrice)
                .orElse(0.0);
    }
}
