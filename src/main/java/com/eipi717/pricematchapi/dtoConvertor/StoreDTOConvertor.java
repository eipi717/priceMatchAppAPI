package com.eipi717.pricematchapi.dtoConvertor;

import com.eipi717.pricematchapi.dtos.StoreDTO;
import com.eipi717.pricematchapi.entity.Store;
import com.eipi717.pricematchapi.utils.DateTimeUtils;

public class StoreDTOConvertor {
    public static StoreDTO convert(Store store) {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreId(store.getStoreId());
        storeDTO.setStoreName(store.getStoreName());
        storeDTO.setCreatedTime(DateTimeUtils.tsToDate(store.getCreatedTime()));

        return storeDTO;
    }
}
