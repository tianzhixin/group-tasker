package com.example.tasker.converters;

import com.example.tasker.entities.GroupEntity;
import com.example.tasker.models.model.Group;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupConverter {

    public Group toModel(GroupEntity entity) {
        Group model = new Group();
        model.setId(entity.getId());
        model.setName(entity.getName());
        return model;
    }

    public List<Group> toModelList(List<GroupEntity> entities) {
        return entities.stream().map(this::toModel).toList();
    }
}
