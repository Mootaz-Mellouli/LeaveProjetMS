package com.example.teamms.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameTeam;
    private String description;
    private boolean archive;
    private Date createdOn;
    @OneToMany(mappedBy = "teamUser")
    @JsonIgnore
    private List<Utilisateur> userList;
    @OneToOne
    @JsonIgnore
    private Utilisateur Utilisateur;

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<Event> eventList;
}
