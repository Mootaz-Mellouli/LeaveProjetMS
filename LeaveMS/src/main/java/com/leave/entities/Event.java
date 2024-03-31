package com.leave.entities;

import jakarta.persistence.*;
import lombok.*;

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

