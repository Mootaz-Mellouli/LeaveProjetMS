package com.leaveManagment.entities;

import java.util.Date;

import lombok.*;
import jakarta.persistence.*;

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
    private User user;
    private boolean isArchived;
    private LeavePriority leavePriority;
    private Boolean teamAvailability;
}
