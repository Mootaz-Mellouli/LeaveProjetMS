package com.event.services;

import org.springframework.util.Assert;

import com.event.entities.Event;
import com.event.repositories.EventRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> retrieveAllEvents() {
        return eventRepository.findAll();
    }

    public Event addEvent(Event e) {
        return eventRepository.save(e);
    }

    public Event updateEvent(Event e) {
        return eventRepository.save(e);
    }

    public Event retrieveEvent(Integer idEvent) {
        return eventRepository.findById(idEvent).orElse(null);
    }

    @Transactional
    public void archiveEvent(Integer idEvent) {
        Event event = eventRepository.findById(idEvent).orElse(null);
        Assert.notNull(event,"Event ne doit pas etre null");
        event.setArchive(true);
        eventRepository.save(event);
    }

    /*@Transactional
    public void affectEventToTeam(Integer idEvent, Integer idTeam) {
        Event event = eventRepository.findById(idEvent).orElse(null);
        Team team = teamRepository.findById(idTeam).orElse(null);
        Assert.notNull(event,"Event ne doit pas etre null");
        Assert.notNull(team,"Team ne doit pas etre null");
        event.setTeam(team);
        eventRepository.save(event);
    }*/

    /*@Transactional
    public void desaffectEventFromTeam(Integer idEvent) {
        Event event = eventRepository.findById(idEvent).orElse(null);
        Assert.notNull(event,"Event ne doit pas etre null");
        event.setTeam(null);
        eventRepository.save(event);
    }*/

    /*public List<Event> getEventByTeam(Integer idTeam) {
        return eventRepository.findByTeam_Id(idTeam);
    }*/
}
