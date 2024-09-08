package com.eipi717.pricematchapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Store")
@Getter
@Setter
public class Store {
    @Column(name = "store_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "created_time")
    private Long createdTime;
}
