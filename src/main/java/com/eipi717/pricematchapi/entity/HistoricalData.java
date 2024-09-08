package com.eipi717.pricematchapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Historicaldata")
@Getter
@Setter

public class HistoricalData {
    @Column(name = "data_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dataId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private Store store;

    @Column(name = "price")
    private Double price;

    @Column(name = "price_per_unit")
    private Double pricePerUnit;

    @Column(name = "unit")
    private String unit;

    @Column(name = "date_of_price")
    private Long dateOfPrice;

    @Column(name = "created_time")
    private Long createdTime;
}
