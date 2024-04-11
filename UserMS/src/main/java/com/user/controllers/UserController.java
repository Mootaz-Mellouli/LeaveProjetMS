package com.user.controllers;


import com.user.entities.Leave;
import com.user.entities.User;
import com.user.services.User.IUserService;
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
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private final IUserService iUserService;

    @Operation(summary = "Retrieve all users", description = "Returns a list of all users")
    @GetMapping()
    @PreAuthorize("hasRole('client_user')")
    public List<User> retrieveAllUsers (){return iUserService.retrieveAllUsers();}

    @Operation(summary = "Retrieve user by matricule", description = "Returns the user with the given matricule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{matricule-id}")
    @PreAuthorize("hasRole('client_admin')")
    public Optional<User> retrieveUser(@PathVariable("matricule-id") String matricule) { return iUserService.retrieveUser(matricule);}

    @Operation(summary = "Add a new user", description = "Adds a new user")
    @PostMapping()
    @PreAuthorize("hasAnyRole('client_admin')")
    public User addUser(@RequestBody User user) { return iUserService.addUser(user); }

    @PreAuthorize("hasAnyRole('client_admin')")
    @PutMapping()
    public User updateTeam(@RequestBody User user) {return iUserService.updateUser(user);}

    @PreAuthorize("hasAnyRole('client_admin')")
    @DeleteMapping("/{matricule-id}")
    public void removeUser(@PathVariable("matricule-id") String matricule) {iUserService.deleteUser(matricule);}

    @Operation(summary = "Remove a user", description = "Removes a user by its matricule")
    @DeleteMapping("/leave/{matricule-id}")
    @PreAuthorize("hasAnyRole('client_admin')")
    public List<Leave> retrieveLeavesByTeam(@PathVariable("matricule-id") String matricule) {
        return iUserService.getLeavesByUser(matricule);
    }

    /*@GetMapping("/team/{team-id}/users")
    public List<User> retrieveUsersByTeam(@PathVariable("team-id") Integer teamId){
        return iUserService.retrieveUsersByTeam(teamId);
    }*/
}
