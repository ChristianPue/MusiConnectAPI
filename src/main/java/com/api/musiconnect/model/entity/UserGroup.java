package com.api.musiconnect.model.entity;

import com.api.musiconnect.model.enums.UserGroupStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_groups")
public class UserGroup
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(nullable = false)
    private UserGroupStatus status;

    @Column(nullable = false)
    private LocalDateTime registerAt;
}