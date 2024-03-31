package com.leave.Feign;

import com.leave.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient("GATEWAY/user")
public interface UserInterface {
    @GetMapping("/{matricule-id}")
    public Optional<User> retrieveUser(@PathVariable("matricule-id") String matricule);

    @GetMapping()
    public List<User> retrieveAllUsers ();
}
