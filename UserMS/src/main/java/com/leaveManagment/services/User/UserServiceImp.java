package com.leaveManagment.services.User;

import com.leaveManagment.config.JWTGenerator;
import com.leaveManagment.dto.AuthResponseDTO;
import com.leaveManagment.dto.LoginDTO;
import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.User;
import com.leaveManagment.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.UUID;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JWTGenerator jwtGenerator;


    @Value("${matricule.length}")
    private int matriculeLength;
    @Override
    public List<User> retrieveAllUsers() {
        System.out.println(userRepository.findAllByIsArchiveFalse());
        return userRepository.findAllByIsArchiveFalse();
    }
    @Override
    public User addUser(User u) {
        Assert.notNull(u,"User is empty");
        User user = userRepository.findUserByMatricule(u.getMatricule()).orElse(null);
        Assert.isNull(user,"This user already exists");
        // Validate the password strength
        String password = u.getPassword();
        Assert.notNull(password, "Password is empty");
        Assert.isTrue(password.length() >= 12, "Password must be at least 12 characters long");
        Assert.isTrue(password.matches(".*[A-Z].*"), "Password must contain at least one uppercase letter");
        Assert.isTrue(password.matches(".*[a-z].*"), "Password must contain at least one lowercase letter");
        Assert.isTrue(password.matches(".*\\d.*"), "Password must contain at least one digit");
        Assert.isTrue(password.matches(".*[\\W_].*"), "Password must contain at least one special character");

        // Set the user's matricule and encode the password
        u.setMatricule(generateMatricule());
        u.setPassword(passwordEncoder.encode(u.getPassword()));

        return userRepository.save(u);
    }
    @Override
    public User updateUser(User u) {
        return userRepository.save(u);
    }
    @Override
    public User retrieveUser(String matricule) {
        return userRepository.findUserByMatricule(matricule).orElse(null);
    }
    @Override
    public void deleteUser(String matricule) {
        User user = userRepository.findUserByMatricule(matricule).orElse(null);
        Assert.notNull(user,"User is empty");
        user.setIsArchive(true);
        user.setArchiveDate(LocalDate.now());
        userRepository.save(user);
    }
    @Override
    public List<Leave> getLeavesByUser(String matricule) {
        User user = userRepository.findUserByMatricule(matricule).orElse(null);
        Assert.notNull(user,"User must be not null");
        List<Leave> leaves = new ArrayList<>();
        user.getLeaves().forEach(leave -> leaves.add(leave) );
        return leaves;
    }
    public User getCurrentUser() {
        return ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }
    @Override
    public AuthResponseDTO loginUser(LoginDTO loginDTO) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getMatricule(),
                            loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(authentication);
            String token = jwtGenerator.generateToken(authentication);
            User user = userRepository.findUserByMatricule(loginDTO.getMatricule()).orElse(null);
            return new AuthResponseDTO(user ,token);
    }
    @Scheduled(cron = "0 0 0 * * *") // Exécuter une fois par jour à minuit
    public void performAutoDelete() {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        List<User> usersToArchive = userRepository.findUsersByArchiveDateBefore(oneYearAgo);
        for (User user : usersToArchive) {
            userRepository.delete(user);
        }
    }
    private String generateMatricule() {
        String uniqueId = UUID.randomUUID().toString();
        String shortId = uniqueId.substring(0, matriculeLength);
        String matricule = "user-" + shortId;

        return matricule;
    }
}
