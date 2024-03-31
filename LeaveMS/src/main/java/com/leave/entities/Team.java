package com.leave.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private List<User> userList;

    @OneToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<Event> eventList;
}