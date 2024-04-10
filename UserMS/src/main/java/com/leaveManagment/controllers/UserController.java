package com.leaveManagment.controllers;


import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.User;
import com.leaveManagment.services.User.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final IUserService iUserService;

    @GetMapping()
    public List<User> retrieveAllUsers (){return iUserService.retrieveAllUsers();}

    @GetMapping("/{matricule-id}")
    public Optional<User> retrieveUser(@PathVariable("matricule-id") String matricule) { return iUserService.retrieveUser(matricule);}

    //@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping()
    public User addUser(@RequestBody User user) { return iUserService.addUser(user); }

    //@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping()
    public User updateTeam(@RequestBody User user) {return iUserService.updateUser(user);}

    //@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping("/{matricule-id}")
    public void removeUser(@PathVariable("matricule-id") String matricule) {iUserService.deleteUser(matricule);}

    //@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/retrieveLeavesByUser/{matricule-id}")
    public List<Leave> retrieveLeavesByTeam(@PathVariable("matricule-id") String matricule) {
        return iUserService.getLeavesByUser(matricule);
    }
    /*@GetMapping("/team/{team-id}/users")
    public List<User> retrieveUsersByTeam(@PathVariable("team-id") Integer teamId){
        return iUserService.retrieveUsersByTeam(teamId);
    }*/
}
