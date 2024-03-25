package com.leaveManagment.controllers;

import com.leaveManagment.dto.AuthResponseDTO;
import com.leaveManagment.dto.LoginDTO;
import com.leaveManagment.services.User.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    private final IUserService iUserService;
    @PostMapping("login")
    public AuthResponseDTO login(@RequestBody LoginDTO loginDTO)  {
        return iUserService.loginUser(loginDTO);
    }
}
