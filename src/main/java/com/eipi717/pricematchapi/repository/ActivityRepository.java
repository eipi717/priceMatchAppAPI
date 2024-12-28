package com.eipi717.pricematchapi.repository;

import com.eipi717.pricematchapi.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    public List<Activity> findActivitiesByUserUserId(Long userId);

    public List<Activity> findActivitiesByCreatedTimeBetween(long startTime, long endTime);

}
