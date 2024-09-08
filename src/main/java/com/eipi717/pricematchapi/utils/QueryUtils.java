package com.eipi717.pricematchapi.utils;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QueryUtils {
    public static Sort.Direction getSortingDirection(String orderBy) {
        if (orderBy.equalsIgnoreCase("DESC")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }
}
