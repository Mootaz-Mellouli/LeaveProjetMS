package com.leaveManagment.controllers;


import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.User;
import com.leaveManagment.services.User.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public List<User> retrieveAllUsers() {
        return iUserService.retrieveAllUsers();
    }

    @Operation(summary = "Retrieve user by matricule", description = "Returns the user with the given matricule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{matricule-id}")
    public Optional<User> retrieveUser(@PathVariable("matricule-id") String matricule) {
        return iUserService.retrieveUser(matricule);
    }

    @Operation(summary = "Add a new user", description = "Adds a new user")
    @PostMapping()
    public User addUser(@RequestBody User user) {
        return iUserService.addUser(user);
    }

    @Operation(summary = "Update a user", description = "Updates an existing user")
    @PutMapping()
    public User updateUser(@RequestBody User user) {
        return iUserService.updateUser(user);
    }

    @Operation(summary = "Remove a user", description = "Removes a user by its matricule")
    @DeleteMapping("/{matricule-id}")
    public void removeUser(@PathVariable("matricule-id") String matricule) {
        iUserService.deleteUser(matricule);
    }

    @Operation(summary = "Retrieve leaves by user", description = "Returns leaves associated with a user")
    @GetMapping("/retrieveLeavesByUser/{matricule-id}")
    public List<Leave> retrieveLeavesByUser(@PathVariable("matricule-id") String matricule) {
        return iUserService.getLeavesByUser(matricule);
    }
}

