package com.leaveManagment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

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
