package com.user.services.User;

import com.user.entities.Leave;
import com.user.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> retrieveAllUsers();

    User addUser(User u);

    User updateUser (User u);

    Optional<User> retrieveUser (String matricule);

    void deleteUser(String matricule);
    List<Leave> getLeavesByUser(String matricule);
    //AuthResponseDTO loginUser(LoginDTO loginDTO);
    User getCurrentUser();
    //List<User> retrieveUsersByTeam(Integer teamId);
}