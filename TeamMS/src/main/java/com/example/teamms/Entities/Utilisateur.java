package com.example.teamms.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Utilisateur {
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
    @OneToMany(mappedBy = "Utilisateur")
    @JsonIgnore
    private List<Leave> leaves;
    @ManyToOne
    @JsonIgnore
    private Team teamUser;
    @OneToMany(mappedBy = "utilisateurClaim")
    @JsonIgnore
    private List<Claim> claim;
    @OneToOne(mappedBy = "Utilisateur")
    @JsonIgnore
    private Team team;
}