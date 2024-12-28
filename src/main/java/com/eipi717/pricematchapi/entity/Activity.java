package com.eipi717.pricematchapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Activity")
@Getter
@Setter

public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long activityId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "created_time")
    private long createdTime;
}
