package com.example.teams.Services;

import com.example.teams.Entities.Leave;
import com.example.teams.Entities.Team;

import java.util.List;
import java.util.Optional;

public interface ITeamService {
    List<Team> retrieveAllTeams();

    Team addTeam(Team t);

    Team updateTeam (Team t);

    Optional<Team> retrieveTeam (Integer idETeam);

    void archiveTeam(Integer idTeam);
   void affectUserToTeam(String  idUser,Integer idTeam);
    void desaffectUserFromTeam(String idUser,Integer idTeam);
    List<Leave> getLeavesByTeam(Integer idTeam);
}