package com.leave.controllers;

import com.leave.entities.Leave;
import com.leave.services.LeaveService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leave")
@CrossOrigin
public class LeaveController {

    private final LeaveService leaveService;
    //@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/all")
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }
    @GetMapping("/{idLeave}")
    public Leave getLeaveById(@PathVariable int idLeave) {
        return leaveService.getLeaveById(idLeave);
    }
    @PostMapping("/add/{matricule}")
    public Leave addLeave(@RequestBody Leave leave,@PathVariable String matricule) {
        return null;
        //return leaveService.addLeave(leave,matricule);
    }
    @PutMapping("/update/{matricule}")
    public Leave updateLeave(@RequestBody Leave leave,@PathVariable String matricule) {
        return null;
        //return leaveService.updateLeave(leave, matricule);
    }
    @DeleteMapping("/delete/{idLeave}")
    public void deleteLeave(@PathVariable int idLeave) {
        leaveService.deleteLeave(idLeave);
    }
    //@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping
    public List<Leave> getAllLeavesNotArchived() {
        return leaveService.getAllLeavesNotArchived();
    }
    //@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/archived")
    public List<Leave> getArchivedLeaves() {
        return leaveService.getArchivedLeaves();
    }
    //@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/me/{matricule}")
    public List<Leave> getLeavesByUser(@PathVariable String matricule) {
        return null;
        //return leaveService.getLeavesByUser(matricule);
    }
}
