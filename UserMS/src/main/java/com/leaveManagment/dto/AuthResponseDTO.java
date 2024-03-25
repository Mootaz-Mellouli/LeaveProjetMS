package com.leaveManagment.dto;

import com.leaveManagment.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";

    private User user;

    public AuthResponseDTO(User user,String accessToken) {
        this.accessToken = accessToken;
        this.user = user;
    }

}
