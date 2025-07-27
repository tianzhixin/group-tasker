package com.example.tasker.webapp.api.controller;

import com.example.tasker.models.model.GroupPage;
import com.example.tasker.skeleton.api.GroupsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group-tasker")
public class GroupController implements GroupsApi {
    private static final Logger LOG = LoggerFactory.getLogger(GroupController.class);

    @Override
    public ResponseEntity<GroupPage> getGroups(Integer size, Integer page){
        return new ResponseEntity<>(new GroupPage(),HttpStatus.OK);
    }

}
