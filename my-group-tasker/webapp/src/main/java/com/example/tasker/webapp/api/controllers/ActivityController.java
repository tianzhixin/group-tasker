package com.example.tasker.webapp.api.controllers;

import com.example.tasker.models.model.ActivityPage;
import com.example.tasker.skeleton.api.ActivitiesApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group-tasker")
public class ActivityController implements ActivitiesApi {
    private static final Logger LOG = LoggerFactory.getLogger(ActivityController.class);

    @Override
    public ResponseEntity<ActivityPage> getActivities(Integer size, Integer page){
        return new ResponseEntity<>(new ActivityPage(), HttpStatus.OK);
    }

}