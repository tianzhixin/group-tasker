package com.example.tasker.webapp.api.Services;

import com.example.tasker.converters.GroupConverter;
import com.example.tasker.entities.GroupEntity;
import com.example.tasker.models.model.GroupPage;
import com.example.tasker.repos.GroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupConverter groupConverter;

    public GroupService(GroupRepository groupRepository, GroupConverter groupConverter) {
        this.groupRepository = groupRepository;
        this.groupConverter = groupConverter;
    }

    public GroupPage getGroups(Integer size, Integer page){
        Optional<Integer> sizeOpt = Optional.ofNullable(size);
        Optional<Integer> pageOpt = Optional.ofNullable(page);

        int pageNumber = pageOpt.orElse(0);
        int pageSize = sizeOpt.orElse(15);

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<GroupEntity> groupEntities = groupRepository.findAll(pageable);

        GroupPage groupPage = new GroupPage();
        groupPage.setContent(groupConverter.toModelList(groupEntities.getContent()));
        groupPage.setPage(pageNumber);
        groupPage.setSize(pageSize);
        groupPage.setTotalElements((int) groupEntities.getTotalElements());
        groupPage.setTotalPages(groupEntities.getTotalPages());
        groupPage.setLast(groupEntities.isLast());
        return groupPage;
    }
}
