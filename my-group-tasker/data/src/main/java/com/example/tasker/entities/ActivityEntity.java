package com.example.tasker.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "activity")
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;
}
