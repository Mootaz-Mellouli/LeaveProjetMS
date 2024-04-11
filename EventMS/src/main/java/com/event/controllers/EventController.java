package com.event.controllers;

import com.event.entities.Event;
import com.event.services.EventService;
import lombok.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@CrossOrigin
public class EventController {


    private final EventService eventService;
    @GetMapping()
    @PreAuthorize("hasRole('client_admin')")
    public List<Event> retrieveAllEvents (){
        return eventService.retrieveAllEvents();
    }

    @GetMapping("/{eventid}")
    @PreAuthorize("hasRole('client_user')")
    public Event retrieveEvent(@PathVariable("eventid") Integer eventId) {
        return eventService.retrieveEvent(eventId);
    }

    @PostMapping()
    @PreAuthorize("hasRole('client_admin')")
    public void addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
    }

    @PutMapping()
    @PreAuthorize("hasRole('client_admin')")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/{eventid}")
    @PreAuthorize("hasRole('client_admin')")
    public void archiveEvent(@PathVariable("eventid") Integer eventId) {
        eventService.archiveEvent(eventId);
    }

    @PutMapping("/{eventid}/{teamid}")
    @PreAuthorize("hasRole('client_admin')")
    public void affectEventToTeam(@PathVariable("eventid") Integer eventid,@PathVariable("teamid") Integer teamid) {
        eventService.affectEventToTeam(eventid,teamid);
    }
    @PutMapping("/desaffectEventToTeam/{eventid}")
    @PreAuthorize("hasRole('client_admin')")
    public void desaffectEventToTeam(@PathVariable("eventid") Integer eventid) {
        eventService.desaffectEventFromTeam(eventid);
    }
    @GetMapping("/retrieveEventByTeam/{teamid}")
    @PreAuthorize("hasRole('client_admin')")
    public List<Event> retrieveEventByTeam(@PathVariable("teamid") Integer teamid) {
        return eventService.getEventByTeam(teamid);
    }
}
