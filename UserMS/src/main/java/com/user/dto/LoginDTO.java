package com.user.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private String matricule;
    private String password;

    @Override
    public String toString() {
        return "LoginDTO{" +
                "matricule='" + matricule + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
