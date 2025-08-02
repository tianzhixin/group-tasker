package com.example.tasker.repos;

import com.example.tasker.entities.GroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<GroupEntity, Long> {
    Page<GroupEntity> findAll(Pageable pageable);
}
