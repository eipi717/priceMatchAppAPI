package com.eipi717.pricematchapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long productId;
    private String productName;
    private String productCategory;
    private String productImage;
    private String productCreatedTime;
    private String productUpdatedTime;
}
