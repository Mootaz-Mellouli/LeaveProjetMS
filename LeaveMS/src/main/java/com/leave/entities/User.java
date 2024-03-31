package com.leave.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String matricule;
    private String firstName;
    private String lastName;
    private String address;
    private String position;
    private Date birthDate;
    private Date startDate;
    private Float leaveBalance;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String phoneNumber;
    private String phoneSecondary;
    private String email;
    private String password;
    private Boolean isArchive = false ;
    private LocalDate archiveDate ;
    private int children;
    @OneToMany
    @JsonIgnore
    private List<Leave> leaves;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team teamUser;
    private Integer teamUserID;
    @OneToMany(mappedBy = "userClaim")
    @JsonIgnore
    private List<Claim> claim;
    @OneToOne
    @JoinColumn(name = "id_team")
    private Team team;
    private Integer teamIdT;
}