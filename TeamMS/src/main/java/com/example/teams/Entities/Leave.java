package com.example.teams.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Conges")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date startDate;
    private Date endDate;
    private String comment;
    @Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus;
    private float nbr_days;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;
    @ManyToOne
    @Transient
    private Utilisateur Utilisateur;
    private String matriculeUser;
    private boolean isArchived;
    private LeavePriority leavePriority;
    private Boolean teamAvailability;
}
