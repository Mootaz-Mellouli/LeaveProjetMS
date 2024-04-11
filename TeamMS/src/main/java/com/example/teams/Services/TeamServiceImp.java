package com.example.teams.Services;

import com.example.teams.Entities.Leave;
import com.example.teams.Entities.Team;
import com.example.teams.Entities.Utilisateur;
import com.example.teams.Feign.LeaveInterface;
import com.example.teams.Repositories.TeamRepository;
import com.example.teams.Feign.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceImp implements ITeamService{

    private final TeamRepository teamRepository;
    @Autowired
    UserInterface userInterface;
    @Autowired
    LeaveInterface leaveInterface;
    @Override
    public List<Team> retrieveAllTeams() {
        List<Team> teams = teamRepository.findByArchiveIsFalse();
        return teams;
    }

    @Override
    public Team addTeam(Team t) {
        return teamRepository.save(t);
    }


    @Override
    public Team updateTeam(Team t) {
        return teamRepository.save(t);
    }

    @Override
    public Optional<Team> retrieveTeam(Integer idETeam) {
        Team team = teamRepository.findById(idETeam).orElse(null);
        return Optional.of(team);
    }

    @Override
    public void archiveTeam(Integer idTeam) {
        Team team = teamRepository.findById(idTeam).orElse(null);
        Assert.notNull(team,"Team ne doit pas etre null");
        team.setArchive(true);
        teamRepository.save(team);
    }

    @Override
    @Transactional
    public void affectUserToTeam(String idUser, Integer idTeam) {
        Utilisateur user = userInterface.retrieveUser(idUser).orElse(null);
        Team team = teamRepository.findById(idTeam).orElse(null);
        Assert.notNull(user,"User ne doit pas etre null");
        Assert.notNull(team,"Team ne doit pas etre null");
        user.setTeamUserID(team.getId());
        userInterface.updateTeam(user);
    }

    @Override
    @Transactional
    public void desaffectUserFromTeam(String idUser, Integer idTeam) {
        Utilisateur user = userInterface.retrieveUser(idUser).orElse(null);
        Team team = teamRepository.findById(idTeam).orElse(null);
        Assert.notNull(user,"User ne doit pas etre null");
        Assert.notNull(team,"Team ne doit pas etre null");
        user.setTeamUserID(null);
        userInterface.updateTeam(user);
    }

    @Override
    public List<Leave> getLeavesByTeam(Integer idTeam) {
        // Retrieve all users from the user microservice
        List<Utilisateur> allUsers = userInterface.retrieveAllUsers();

        // Filter users based on the team ID
        List<Utilisateur> usersInTeam = allUsers.stream()
                .filter(user -> user.getTeamUser().getId() == idTeam)
                .collect(Collectors.toList());

        List<Leave> allLeaves = new ArrayList<>();

        // For each user in the team, collect their leaves
        for (Utilisateur user : usersInTeam) {
            List<Leave> userLeaves = leaveInterface.getLeavesByUser(user.getMatricule());
            for (Leave leave : userLeaves) {
                // Retrieve user from userInterface and set it to leave
                leave.setUtilisateur(userInterface.retrieveUser(user.getMatricule()).get());
            }
            allLeaves.addAll(userLeaves);
        }

        return allLeaves;
    }
    /*private void setUserListAndDesiredUserForTeam(Team team) {
        // Populate userListIds
        List<String> userListIds = team.getUserListIds();
        if (userListIds != null && !userListIds.isEmpty()) {
            List<Utilisateur> userList = new ArrayList<>();
            for (String userId : userListIds) {
                Optional<Utilisateur> userOptional = userInterface.retrieveUser(userId);
                userOptional.ifPresent(userList::add);
            }
            team.setUserList(userList);
        }

        // Find the desired user for the team using the teamUser relationship
        Utilisateur desiredUser = null;
        if (team.getUtilisateurID() != null) {
            Optional<Utilisateur> userOptional = userInterface.retrieveUser(team.getUtilisateurID());
            desiredUser = userOptional.orElse(null);
            team.setUtilisateur(desiredUser);
        }
    }*/
}