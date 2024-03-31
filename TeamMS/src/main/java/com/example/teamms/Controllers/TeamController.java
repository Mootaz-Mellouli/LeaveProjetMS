package com.example.teamms.Controllers;

import com.example.teamms.Entities.Leave;
import com.example.teamms.Entities.Team;
import com.example.teamms.Services.ITeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TeamController {
    private final ITeamService teamService;
    @GetMapping()
    public List<Team> retrieveAllTeams (){
        return teamService.retrieveAllTeams();
    }

    @GetMapping("/{team-id}")
    public Optional<Team> retrieveTeam(@PathVariable("team-id") Integer teamId) {
        return teamService.retrieveTeam(teamId);
    }

    @PostMapping()
    public void addTeam(@RequestBody Team team) {

        teamService.addTeam(team);
    }

    @PutMapping()
    public Team updateTeam(@RequestBody Team team) {
        return teamService.updateTeam(team);
    }

    @DeleteMapping("/{team-id}")
    public void archiveTeam(@PathVariable("team-id") Integer teamId) {
        teamService.archiveTeam(teamId);
    }

    @PutMapping("/{user-id}/{team-id}")
    public void affectUserToTeam(@PathVariable("user-id") String userid,@PathVariable("team-id") Integer teamId) {
        teamService.affectUserToTeam(userid,teamId);
    }
    @PutMapping("/desaffectUserToTeam/{user-id}/{team-id}")
    public void desaffectUserToTeam(@PathVariable("user-id") String userid,@PathVariable("team-id") Integer teamId) {
        teamService.desaffectUserFromTeam(userid,teamId);
    }
    @GetMapping("/retrieveLeavesByTeam/{team-id}")
    public List<Leave> retrieveLeavesByTeam(@PathVariable("team-id") Integer teamId) {
        return teamService.getLeavesByTeam(teamId);
    }
}
