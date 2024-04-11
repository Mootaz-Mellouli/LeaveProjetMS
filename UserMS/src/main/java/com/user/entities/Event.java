package com.user.entities;

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
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String eventTitle;
    private Date dateEvent;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String description;
    private boolean archive;
    @ManyToOne
    private Team team;
}
