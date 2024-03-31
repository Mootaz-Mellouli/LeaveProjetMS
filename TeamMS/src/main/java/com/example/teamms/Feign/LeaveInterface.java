package com.example.teamms.Feign;

import com.example.teamms.Entities.Leave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("GATEWAY/leave")

public interface LeaveInterface {
    @GetMapping("/me/{matricule}")
    public List<Leave> getLeavesByUser(@PathVariable String matricule);
}
