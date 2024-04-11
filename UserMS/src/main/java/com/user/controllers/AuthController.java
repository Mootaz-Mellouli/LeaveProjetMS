package com.user.controllers;

import com.user.services.User.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {
    private final IUserService iUserService;
    /*
    @PostMapping("login")
    public AuthResponseDTO login(@RequestBody LoginDTO loginDTO)  {
        return iUserService.loginUser(loginDTO);
    }

     */
}
