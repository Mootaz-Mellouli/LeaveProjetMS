package com.example.teamms.Controllers;

import com.example.teamms.Entities.Leave;
import com.example.teamms.Entities.Team;
import com.example.teamms.Services.ITeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public List<Team> retrieveAllTeams(Principal principal) {
        return teamService.retrieveAllTeams();
    }

    @Operation(summary = "Retrieve team by ID", description = "Returns the team with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved team"),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
    @GetMapping("/{team-id}")
    public Optional<Team> retrieveTeam(@PathVariable("team-id") Integer teamId) {
        return teamService.retrieveTeam(teamId);
    }

    @Operation(summary = "Add a new team", description = "Adds a new team")
    @PostMapping()
    public void addTeam(@RequestBody Team team) {
        teamService.addTeam(team);
    }

    @Operation(summary = "Update a team", description = "Updates an existing team")
    @PutMapping()
    public Team updateTeam(@RequestBody Team team) {
        return teamService.updateTeam(team);
    }

    @Operation(summary = "Archive a team", description = "Archives a team by its ID")
    @DeleteMapping("/{team-id}")
    public void archiveTeam(@PathVariable("team-id") Integer teamId) {
        teamService.archiveTeam(teamId);
    }

    @Operation(summary = "Assign user to team", description = "Assigns a user to a team")
    @PutMapping("/{user-id}/{team-id}")
    public void affectUserToTeam(@PathVariable("user-id") String userId, @PathVariable("team-id") Integer teamId) {
        teamService.affectUserToTeam(userId, teamId);
    }

    @Operation(summary = "Remove user from team", description = "Removes a user from a team")
    @PutMapping("/desaffectUserToTeam/{user-id}/{team-id}")
    public void desaffectUserToTeam(@PathVariable("user-id") String userId, @PathVariable("team-id") Integer teamId) {
        teamService.desaffectUserFromTeam(userId, teamId);
    }

    @Operation(summary = "Retrieve leaves by team", description = "Returns leaves associated with a team")
    @GetMapping("/retrieveLeavesByTeam/{team-id}")
    public List<Leave> retrieveLeavesByTeam(@PathVariable("team-id") Integer teamId) {
        return teamService.getLeavesByTeam(teamId);
    }
}
