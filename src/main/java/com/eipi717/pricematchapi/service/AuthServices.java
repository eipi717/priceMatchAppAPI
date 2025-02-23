package com.eipi717.pricematchapi.service;

import com.eipi717.pricematchapi.entity.Activity;
import com.eipi717.pricematchapi.entity.User;
import com.eipi717.pricematchapi.repository.ActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {
    private final ActivityRepository activityRepository;
    private final UserService userService;

    public AuthServices(ActivityRepository activityRepository, UserService userService) {
        this.activityRepository = activityRepository;
        this.userService = userService;
    }

    // TODO: Login with JWT, Logout

}
