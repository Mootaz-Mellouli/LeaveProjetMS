package com.event.repositories;

import com.event.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer>{
    //@Query("select e from Event e where e.team.id = ?1")
    //List<Event> findByTeam_Id(Integer teamId);
}
