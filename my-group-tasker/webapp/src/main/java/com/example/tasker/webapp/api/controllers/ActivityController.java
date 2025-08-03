package com.example.tasker.webapp.api.controllers;

import com.example.tasker.models.model.ActivityPage;
import com.example.tasker.skeleton.api.ActivitiesApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group-tasker")
@Slf4j
public class ActivityController implements ActivitiesApi {
    @Override
    public ResponseEntity<ActivityPage> getActivities(Integer size, Integer page){
        return new ResponseEntity<>(new ActivityPage(), HttpStatus.OK);
    }

}