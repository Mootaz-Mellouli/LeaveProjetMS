package com.event.services;

import com.event.Feign.TeamInterface;
import com.event.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.event.entities.Event;
import com.event.repositories.EventRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    @Autowired
    TeamInterface teamInterface;
    public List<Event> retrieveAllEvents() {
        List<Event> allEvents = eventRepository.findAll();
        for (Event event : allEvents) {
            if (event != null && event.getTeamId() != null) {
                Team team = teamInterface.retrieveTeam(event.getTeamId()).orElse(null);
                event.setTeam(team);
            }
        }
        return allEvents;
    }

    public Event addEvent(Event e) {
        return eventRepository.save(e);
    }

    public Event updateEvent(Event e) {
        return eventRepository.save(e);
    }
    public Event retrieveEvent(Integer idEvent) {
        Event event = eventRepository.findById(idEvent).orElse(null);
        if(event != null && event.getTeamId() != null){
                Team team = teamInterface.retrieveTeam(event.getTeamId()).orElse(null);
                event.setTeam(team);
        }
        return event;
    }

    @Transactional
    public void archiveEvent(Integer idEvent) {
        Event event = eventRepository.findById(idEvent).orElse(null);
        Assert.notNull(event,"Event ne doit pas etre null");
        event.setArchive(true);
        eventRepository.save(event);
    }

    @Transactional
    public void affectEventToTeam(Integer idEvent, Integer idTeam) {
        Event event = eventRepository.findById(idEvent).orElse(null);
        Team team = teamInterface.retrieveTeam(idTeam).orElse(null);
        Assert.notNull(event,"Event ne doit pas etre null");
        Assert.notNull(team,"Team ne doit pas etre null");
        event.setTeamId(team.getId());
        eventRepository.save(event);
    }

    @Transactional
    public void desaffectEventFromTeam(Integer idEvent) {
        Event event = eventRepository.findById(idEvent).orElse(null);
        Assert.notNull(event,"Event ne doit pas etre null");
        event.setTeamId(null);
        eventRepository.save(event);
    }

    public List<Event> getEventByTeam(Integer idTeam) {
        List<Event> allEvents = retrieveAllEvents();
        List<Event> eventsByTeam = new ArrayList<>();

        for (Event event : allEvents) {
            if (event != null && event.getTeamId() != null && event.getTeamId().equals(idTeam)) {
                eventsByTeam.add(event);
            }
        }
        return eventsByTeam;
    }
}
