package com.eipi717.pricematchapi.controller;

import com.eipi717.pricematchapi.entity.Activity;
import com.eipi717.pricematchapi.response.SelfDefinedResponse;
import com.eipi717.pricematchapi.service.AdminServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {
    private final AdminServices adminServices;

    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @GetMapping(value = "findAllActivities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllActivity() {
        SelfDefinedResponse<List<Activity>> selfDefinedResponse = new SelfDefinedResponse();
        List<Activity> activities = adminServices.findAllActivities();

        selfDefinedResponse.setCount(activities.size());
        selfDefinedResponse.setData(activities);
        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "findActivitiesByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findActivityByUserId(
            @RequestParam Long userId
    ) {
        SelfDefinedResponse<List<Activity>> selfDefinedResponse = new SelfDefinedResponse();
        List<Activity> activities = adminServices.findActivityByUserId(userId);

        selfDefinedResponse.setCount(activities.size());
        selfDefinedResponse.setData(activities);

        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "findActivitiesBetweenDates")
    public ResponseEntity<?> findActivitiesBetweenDates(
            @RequestParam Long startDate,
            @RequestParam Long endDate
    ) {
        SelfDefinedResponse<List<Activity>> selfDefinedResponse = new SelfDefinedResponse();
        List<Activity> activities = adminServices.findActivityBetweenDates(startDate, endDate);

        selfDefinedResponse.setCount(activities.size());
        selfDefinedResponse.setData(activities);

        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "deleteActivitiesBetweenDates")
    public ResponseEntity<?> deleteActivitiesByDate(
            @RequestParam Long startDate,
            @RequestParam Long endDate
    ) {
        SelfDefinedResponse<List<Activity>> selfDefinedResponse = new SelfDefinedResponse();
        long deletedCount = adminServices.deleteActivitiesBetweenDates(startDate, endDate);

        selfDefinedResponse.setMessage("Delete completed!");
        selfDefinedResponse.setCount(deletedCount);

        return new ResponseEntity<>(selfDefinedResponse, HttpStatus.OK);
    }
}
