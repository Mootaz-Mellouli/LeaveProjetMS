package com.example.teams.Controllers;

import com.example.teams.Entities.Leave;
import com.example.teams.Entities.Team;
import com.example.teams.Services.ITeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Team Management", description = "APIs for managing teams")
public class TeamController {
    private final ITeamService teamService;

    @Operation(summary = "Retrieve all teams", description = "Returns a list of all teams")
    @GetMapping()
    @PreAuthorize("hasRole('client_user')")
    public List<Team> retrieveAllTeams (){
        return teamService.retrieveAllTeams();
    }

    @Operation(summary = "Retrieve team by ID", description = "Returns the team with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved team"),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
    @GetMapping("/{team-id}")
    @PreAuthorize("hasRole('client_admin')")
    public Optional<Team> retrieveTeam(@PathVariable("team-id") Integer teamId) {
        return teamService.retrieveTeam(teamId);
    }

    @Operation(summary = "Add a new team", description = "Adds a new team")
    @PostMapping()
    @PreAuthorize("hasRole('client_admin')")
    public void addTeam(@RequestBody Team team) {
        teamService.addTeam(team);
    }

    @Operation(summary = "Update a team", description = "Updates an existing team")
    @PutMapping()
    @PreAuthorize("hasRole('client_admin')")
    public Team updateTeam(@RequestBody Team team) {
        return teamService.updateTeam(team);
    }

    @Operation(summary = "Archive a team", description = "Archives a team by its ID")
    @DeleteMapping("/{team-id}")
    @PreAuthorize("hasRole('client_admin')")
    public void archiveTeam(@PathVariable("team-id") Integer teamId) {
        teamService.archiveTeam(teamId);
    }

    @Operation(summary = "Assign user to team", description = "Assigns a user to a team")
    @PutMapping("/{user-id}/{team-id}")
    @PreAuthorize("hasRole('client_admin')")
    public void affectUserToTeam(@PathVariable("user-id") String userid,@PathVariable("team-id") Integer teamId) {
        teamService.affectUserToTeam(userid,teamId);
    }
    @Operation(summary = "Remove user from team", description = "Removes a user from a team")
    @PutMapping("/desaffectUserToTeam/{user-id}/{team-id}")
    @PreAuthorize("hasRole('client_admin')")
    public void desaffectUserToTeam(@PathVariable("user-id") String userid,@PathVariable("team-id") Integer teamId) {
        teamService.desaffectUserFromTeam(userid,teamId);
    }
    @Operation(summary = "Retrieve leaves by team", description = "Returns leaves associated with a team")
    @GetMapping("/retrieveLeavesByTeam/{team-id}")
    @PreAuthorize("hasRole('client_admin')")
    public List<Leave> retrieveLeavesByTeam(@PathVariable("team-id") Integer teamId) {
        return teamService.getLeavesByTeam(teamId);
    }
}
