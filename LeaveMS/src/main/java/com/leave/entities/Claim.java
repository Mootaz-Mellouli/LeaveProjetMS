package com.leave.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @Enumerated(EnumType.STRING)
    private ClaimPriority claimPriority;
    private Date dateClaim;
    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus;
    private boolean feedBackEmployee;
    @ManyToOne
    private User userClaim;
}