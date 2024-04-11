package com.leaveManagment.services.User;

import com.leaveManagment.Feign.LeaveInterface;
import com.leaveManagment.Feign.TeamInterface;
import com.leaveManagment.dto.AuthResponseDTO;
import com.leaveManagment.dto.LoginDTO;
import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.Team;
import com.leaveManagment.entities.User;
import com.leaveManagment.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Optional;
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

    //private final JWTGenerator jwtGenerator;

    @Autowired
    private TeamInterface teamInterface;

    @Autowired
    private LeaveInterface leaveInterface;
    @Value("${matricule.length}")
    private int matriculeLength;
    @Override
    public List<User> retrieveAllUsers() {
        List<User> users = userRepository.findAllByIsArchiveFalse();
        List<User> usersWithTeams = new ArrayList<>();
        for (User user : users) {
            setUserTeam(user);
            usersWithTeams.add(user);
        }
        return usersWithTeams;
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
        updateTeamInformation(u);
        return userRepository.save(u);
    }
    @Override
    public User updateUser(User u) {
        updateTeamInformation(u);
        return userRepository.save(u);
    }
    private void updateTeamInformation(User u) {
        if (u.getTeamUserID() != null) {
            Optional<Team> teamUser = teamInterface.retrieveTeam(u.getTeamUserID());
            if (teamUser.isPresent()) {
                u.setTeamUserID(teamUser.get().getId());
            }
        } else {
            u.setTeamUserID(null);
        }
        if (u.getTeam() != null) {
            Optional<Team> team = teamInterface.retrieveTeam(u.getTeam().getId());
            if (team.isPresent()) {
                u.setTeamIdT(team.get().getId());
            }
        } else {
            u.setTeamIdT(null);
        }
    }
    @Override
    public Optional<User> retrieveUser(String matricule) {
        Optional<User> optionalUser = userRepository.findUserByMatricule(matricule);
        optionalUser.ifPresent(this::setUserTeam);
        return optionalUser;
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
        Assert.notNull(user, "User must not be null");

        // Retrieve leaves for the user using the LeaveInterface
        List<Leave> userLeaves = leaveInterface.getLeavesByUser(matricule);

        // You may want to perform additional processing here if needed

        return userLeaves;
    }
    public User getCurrentUser() {
        return ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }
    /*@Override
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
    }*/
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
    private void setUserTeam(User user) {
        // Check if user has a team associated
        if (user.getTeamUserID() != null) {
            Optional<Team> team = teamInterface.retrieveTeam(user.getTeamUserID());
            // Set the team in the user if it exists
            team.ifPresentOrElse(
                    t -> user.setTeamUser(team.orElse(null)),
                    () -> {
                        // Handle case where team is not found
                        System.out.println("TeamUser not found for user: " + user.getMatricule());
                        // You can throw an exception, log an error, or handle it in another appropriate way
                    }
            );
        }
        if (user.getTeamIdT() != null) {
            Optional<Team> team = teamInterface.retrieveTeam(user.getTeamIdT());
            // Set the team in the user if it exists
            team.ifPresentOrElse(
                    t -> user.setTeam(team.orElse(null)),
                    () -> {
                        // Handle case where team is not found
                        System.out.println("Team not found for user: " + user.getMatricule());
                        // You can throw an exception, log an error, or handle it in another appropriate way
                    }
            );
        }
    }

    /*@Override
    public List<User> retrieveUsersByTeam(Integer teamId) {
        return userRepository.findByTeamId(teamId); // Assuming findByTeamIdi() is a method in your repository
    }*/
}
