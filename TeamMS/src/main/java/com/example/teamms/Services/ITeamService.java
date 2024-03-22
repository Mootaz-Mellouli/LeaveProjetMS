package com.example.teamms.Services;

import com.example.teamms.Entities.Leave;
import com.example.teamms.Entities.Team;

import java.util.List;

public interface ITeamService {
    List<Team> retrieveAllTeams();

    Team addTeam(Team t);

    Team updateTeam (Team t);

    Team retrieveTeam (Integer idETeam);

    void archiveTeam(Integer idTeam);
   /*void affectUserToTeam(Integer idUser,Integer idTeam);
    void desaffectUserFromTeam(Integer idUser,Integer idTeam);
    List<Leave> getLeavesByTeam(Integer idTeam);*/
}