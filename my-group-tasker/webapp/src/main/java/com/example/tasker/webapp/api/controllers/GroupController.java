package com.example.tasker.webapp.api.controllers;

import com.example.tasker.models.model.GroupPage;
import com.example.tasker.skeleton.api.GroupsApi;
import com.example.tasker.webapp.api.Services.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/group-tasker")
@Slf4j
public class GroupController implements GroupsApi {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public ResponseEntity<GroupPage> getGroups(Integer size, Integer page){
        GroupPage groupPage = groupService.getGroups(size,page);
        return ResponseEntity.ok(groupPage);
    }

}
