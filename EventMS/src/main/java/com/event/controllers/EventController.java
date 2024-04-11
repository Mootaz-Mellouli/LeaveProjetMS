package com.event.controllers;

import com.event.entities.Event;
import com.event.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Event Management", description = "APIs for managing events")
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Retrieve all events", description = "Returns a list of all events")
    @GetMapping()
    public List<Event> retrieveAllEvents() {
        return eventService.retrieveAllEvents();
    }

    @Operation(summary = "Retrieve event by ID", description = "Returns the event with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved event"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{eventid}")
    public Event retrieveEvent(@PathVariable("eventid") Integer eventId) {
        return eventService.retrieveEvent(eventId);
    }

    @Operation(summary = "Add a new event", description = "Adds a new event")
    @PostMapping()
    public void addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
    }

    @Operation(summary = "Update an event", description = "Updates an existing event")
    @PutMapping()
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

    @Operation(summary = "Archive an event", description = "Archives an existing event")
    @DeleteMapping("/{eventid}")
    public void archiveEvent(@PathVariable("eventid") Integer eventId) {
        eventService.archiveEvent(eventId);
    }

    @Operation(summary = "Assign event to team", description = "Assigns an event to a team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully assigned event to team"),
            @ApiResponse(responseCode = "404", description = "Event or team not found")
    })
    @PutMapping("/{eventid}/{teamid}")
    public void affectEventToTeam(@PathVariable("eventid") Integer eventId,
                                  @PathVariable("teamid") Integer teamId) {
        eventService.affectEventToTeam(eventId, teamId);
    }

    @Operation(summary = "Remove event from team", description = "Removes an event from a team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully removed event from team"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @PutMapping("/desaffectEventToTeam/{eventid}")
    public void desaffectEventToTeam(@PathVariable("eventid") Integer eventId) {
        eventService.desaffectEventFromTeam(eventId);
    }

    @Operation(summary = "Retrieve events by team", description = "Returns events associated with a team")
    @GetMapping("/retrieveEventByTeam/{teamid}")
    public List<Event> retrieveEventByTeam(@PathVariable("teamid") Integer teamId) {
        return eventService.getEventByTeam(teamId);
    }
}
