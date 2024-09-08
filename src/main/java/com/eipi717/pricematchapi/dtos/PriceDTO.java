package com.eipi717.pricematchapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceDTO {
    private Long priceId;

    private String productName;

    private String productCategory;

    private String storeName;

    private Double price;

    private String size;

    private Double pricePerUnit;

    private String unit;

    private String startDate;

    private String endDate;

    private String updatedTime;
}
