package com.example.teams.Feign;

import com.example.teams.Entities.Utilisateur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient("GATEWAY/user")
public interface UserInterface {
    @GetMapping("/{matricule-id}")
    public Optional<Utilisateur> retrieveUser(@PathVariable("matricule-id") String matricule);

    @GetMapping("")
    public List<Utilisateur> retrieveAllUsers ();
    @GetMapping("/team/{team-id}/users")
    public List<Utilisateur> retrieveUsersByTeam(@PathVariable("team-id") Integer teamId);
    @PutMapping()
    public Utilisateur updateTeam(@RequestBody Utilisateur user);
}
