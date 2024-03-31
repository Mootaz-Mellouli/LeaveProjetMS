package com.leave.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="conge")
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
    private User user;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String matriculeUser;
    private boolean isArchived;
    private boolean teamAvailability;
}
