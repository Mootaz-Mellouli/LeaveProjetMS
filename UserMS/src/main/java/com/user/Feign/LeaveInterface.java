package com.user.Feign;

import com.user.entities.Leave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient("GATEWAY/leave")
public interface LeaveInterface {
    @GetMapping("/all")
    public List<Leave> getAllLeaves();
    @GetMapping("/me/{matricule}")
    public List<Leave> getLeavesByUser(@PathVariable String matricule);
}
