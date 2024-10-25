package com.eipi717.pricematchapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductWithPriceDTO extends ProductDTO{
    private Double currentPrice;
}
