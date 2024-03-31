package com.event.Feign;

import com.event.entities.Team;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient("GATEWAY/team")
public interface TeamInterface {
    @GetMapping("/{team-id}")
    public Optional<Team> retrieveTeam(@PathVariable("team-id") Integer teamId);
}