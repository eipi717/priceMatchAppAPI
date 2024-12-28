package com.eipi717.pricematchapi.service;

import com.eipi717.pricematchapi.entity.Activity;
import com.eipi717.pricematchapi.repository.ActivityRepository;
import com.eipi717.pricematchapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServices {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public AdminServices(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public List<Activity> findAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> findActivityByUserId(Long userId) {
        return activityRepository.findActivitiesByUserUserId(userId);
    }

    public List<Activity> findActivityBetweenDates(Long startDate, Long endDate) {
        return activityRepository.findActivitiesByCreatedTimeBetween(startDate, endDate);
    }

    public long deleteActivitiesBetweenDates(Long startDate, Long endDate) {
        List<Activity> activities = activityRepository.findActivitiesByCreatedTimeBetween(startDate, endDate);
        activityRepository.deleteAll(activities);
        activityRepository.flush();

        return activities.size();
    }
}
