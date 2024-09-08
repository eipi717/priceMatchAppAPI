package com.eipi717.pricematchapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoricalDataDTO {
    private long dataId;
    private String productName;
    private String storeName;
    private Double price;
    private Double pricePerUnit;
    private String unit;
    private long dateOfPrice;
    private long createdTime;

    private HistoricalDataDTO(long dataId, String productName, String storeName, Double price, Double pricePerUnit, String unit, long dateOfPrice, long createdTime) {
        this.dataId = dataId;
        this.productName = productName;
        this.storeName = storeName;
        this.price = price;
        this.pricePerUnit = pricePerUnit;
        this.unit = unit;
        this.dateOfPrice = dateOfPrice;
        this.createdTime = createdTime;
    }

    // Static Builder Class
    public static class Builder {
        private long dataId;
        private String productName;
        private String storeName;
        private Double price;
        private Double pricePerUnit;
        private String unit;
        private long dateOfPrice;
        private long createdTime;

        public Builder setDataId(long dataId) {
            this.dataId = dataId;
            return this;
        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setStoreName(String storeName) {
            this.storeName = storeName;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setPricePerUnit(Double pricePerUnit) {
            this.pricePerUnit = pricePerUnit;
            return this;
        }

        public Builder setUnit(String unit) {
            this.unit = unit;
            return this;
        }

        public Builder setDateOfPrice(long dateOfPrice) {
            this.dateOfPrice = dateOfPrice;
            return this;
        }

        public Builder setCreatedTime(long createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public HistoricalDataDTO build() {
            return new HistoricalDataDTO(dataId, productName, storeName, price, pricePerUnit, unit, dateOfPrice, createdTime);
        }
    }
}
