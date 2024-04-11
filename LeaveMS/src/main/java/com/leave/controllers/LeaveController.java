package com.leave.controllers;

import com.leave.entities.Leave;
import com.leave.services.LeaveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leave")
@CrossOrigin
@Tag(name = "Leave Management", description = "APIs for managing leaves")
public class LeaveController {

    private final LeaveService leaveService;

    @Operation(summary = "Get all leaves", description = "Retrieve all leaves")
    @GetMapping("/all")
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @Operation(summary = "Get leave by ID", description = "Retrieve a leave by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved leave"),
            @ApiResponse(responseCode = "404", description = "Leave not found")
    })
    @GetMapping("/{idLeave}")
    public Leave getLeaveById(@PathVariable int idLeave) {
        return leaveService.getLeaveById(idLeave);
    }

    @Operation(summary = "Add a leave", description = "Add a new leave for a user")
    @PostMapping("/add/{matricule}")
    public Leave addLeave(@RequestBody Leave leave, @PathVariable String matricule) {
        return leaveService.addLeave(leave, matricule);
    }

    @Operation(summary = "Update a leave", description = "Update an existing leave")
    @PutMapping("/update/{matricule}")
    public Leave updateLeave(@RequestBody Leave leave, @PathVariable String matricule) {
        return leaveService.updateLeave(leave, matricule);
    }

    @Operation(summary = "Delete a leave", description = "Delete a leave by its ID")
    @DeleteMapping("/delete/{idLeave}")
    public void deleteLeave(@PathVariable int idLeave) {
        leaveService.deleteLeave(idLeave);
    }

    @Operation(summary = "Get all leaves not archived", description = "Retrieve all leaves that are not archived")
    @GetMapping
    public List<Leave> getAllLeavesNotArchived() {
        return leaveService.getAllLeavesNotArchived();
    }

    @Operation(summary = "Get archived leaves", description = "Retrieve all archived leaves")
    @GetMapping("/archived")
    public List<Leave> getArchivedLeaves() {
        return leaveService.getArchivedLeaves();
    }

    @Operation(summary = "Get leaves by user", description = "Retrieve leaves for a specific user")
    @GetMapping("/me/{matricule}")
    public List<Leave> getLeavesByUser(@PathVariable String matricule) {
        return leaveService.getLeavesByUser(matricule);
    }
}

