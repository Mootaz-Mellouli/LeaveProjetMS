package com.leave.services;

import com.leave.Feign.UserInterface;
import com.leave.entities.*;
import com.leave.repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@EnableScheduling
public class LeaveService {
    private final LeaveRepository leaveRepository;
    @Autowired
    UserInterface userInterface;
    public List<Leave> getAllLeaves() {
        List<Leave> leaves = leaveRepository.findAll();
        for (Leave leave : leaves) {
            Optional<User> optionalUser = userInterface.retrieveUser(leave.getMatriculeUser());
            optionalUser.ifPresent(leave::setUser);
        }
        return leaves;
    }
    @SneakyThrows
    public Leave getLeaveById(int idLeave) {
        isLeaveArchived(idLeave);
        Leave leave = leaveRepository.findById(idLeave).orElseThrow(ChangeSetPersister.NotFoundException::new);

        // Retrieve user from UserMS using Feign Client
        Optional<User> userOptional = userInterface.retrieveUser(leave.getMatriculeUser());
        if (userOptional.isPresent()) {
            leave.setUser(userOptional.get());
        }

        return leave;
    }
    /*@SneakyThrows
    public Leave addLeave(Leave leave, String matricule) {
        // TODO : number of days => demi-journéé actif seulement quand on choisit la meme date
        // ne7sbou ken wakt el type congé solde
        *//*User user = userService.getCurrentUser2();
        if ( user != null) {
            leave.setUser(user);
        }*//*
        Boolean teamAvailability;
        User user = userRepository.findUserByMatricule(matricule).orElse(null);
        if (user != null) {
            leave.setUser(user);
            leave.setTeamAvailability(checkTeamAvailability(user, leave));
        }
        if (leave.getStartDate().after(leave.getEndDate()) || (leave.getEndDate().before(leave.getStartDate()))) {
            throw new IllegalAccessException();
        }
        leave.setLeavePriority(getLeavePriority(leave.getLeaveType()));
        leave.setLeaveStatus(LeaveStatus.IN_PROGRESS);

        *//*if (leave.getLeaveType() == LeaveType.CG_PAYE) {
            leave.setLeavePriority(checkLeavePriorityCGPaye(user));
        }*//*
        return leaveRepository.save(leave);
    }*/
    @SneakyThrows
    public Leave updateLeave(Leave leave, String matricule) {
        isLeaveArchived(leave.getId());

        Optional<User> userOptional = userInterface.retrieveUser(matricule);
        if (userOptional.isPresent()) {
            leave.setMatriculeUser(userOptional.get().getMatricule());
            return leaveRepository.save(leave);
        }
        return leave;
    }

    public void deleteLeave(int idLeave) {
        Leave leave = leaveRepository.findById(idLeave).orElse(null);
        Assert.notNull(leave, "leave not found");
        if (!leave.isArchived()) {
            leave.setArchived(true);
        }
    }

    public List<Leave> getLeavesByUser(String matricule) {
        User user = userInterface.retrieveUser(matricule).orElse(null);
        if (user != null) {
            // Fetch all leaves for the user
            List<Leave> allLeaves = getAllLeaves();

            // Filter leaves based on whether they are archived or not
            List<Leave> leaves = allLeaves.stream()
                    .filter(leave -> !leave.isArchived() && leave.getUser().getMatricule().equals(matricule))
                    .collect(Collectors.toList());

            return leaves;
        }
        return null;
    }

    public List<Leave> getAllLeavesNotArchived() {
        List<Leave> leaves = leaveRepository.getLeavesByIsArchivedIsFalse();
        for (Leave leave : leaves) {
            Optional<User> optionalUser = userInterface.retrieveUser(leave.getMatriculeUser());
            optionalUser.ifPresent(leave::setUser);
        }
        return leaves;
    }

    public List<Leave> getArchivedLeaves() {
        List<Leave> leaves = leaveRepository.getLeavesByIsArchivedIsTrue();
        for (Leave leave : leaves) {
            Optional<User> optionalUser = userInterface.retrieveUser(leave.getMatriculeUser());
            optionalUser.ifPresent(leave::setUser);
        }
        return leaves;
    }

    public void isLeaveArchived(int idLeave) throws Exception {
        Leave leave = leaveRepository.findById(idLeave).orElse(null);
        if (leave != null && leave.isArchived()) {
            throw new IllegalAccessException();
        }
    }

    /*public void calculConge(User user, Leave leave) {
        // 2.5 jours par mois
        // 30 jours max par an
        // anciennete de salarié dans l'entreprise
        // situation familial
        // congé d'été => 24 jours max
        // quand le congé approuvé la calendrier est mis a jour
        float leaveBalance = user.getLeaveBalance();
        //LeavePriority leavePriority = getLeavePriority(leave.getLeaveType(), user);
        if (leaveBalance > 0)
        {


        }
    }*/

    //@Scheduled(cron="0 0 8 1 1/1 ? *")
    // @Scheduled(fixedRate = 1000)
    /*public void soldeConge() {
        System.out.println("balance updated");
        // TODO : shceduler a chaque fin d'annee remize a zero du solde
        userRepository.findAll().forEach(user -> {
            Float oldBalance = user.getLeaveBalance();
            Float newBalance = oldBalance + 2;
            user.setLeaveBalance(newBalance);
        });
    }*/

    /*public LeavePriority getLeavePriority(LeaveType leaveType)
    {
        // TODO : USER.getchildredn
        switch(leaveType) {
            case EVEN_FAM_DECES:
            case CG_MALADIE:
                return LeavePriority.HIGH;
            case CG_MATERN:
                return LeavePriority.MEDIUM;
            case CG_PATERN:
                return LeavePriority.MEDIUM;
            default:
                return LeavePriority.LOW;
        }
    }*/

    /*public Boolean checkTeamAvailability(User user, Leave leave) {
        Team team = user.getTeamUser();
        List<User> userList = userRepository.findUsersByTeamUserAndLeavesStartDateGreaterThanAndLeavesEndDateLessThan(
                team,
                leave.getStartDate(),
                leave.getEndDate()
        );
        System.out.println(userList.size());
        float numberUsersByTeam = team.getUserList().size(); //5
        int pourcentage = Math.round((30.0f * numberUsersByTeam) / 100.0f); // 1.5
        System.out.println(numberUsersByTeam);
        System.out.println(pourcentage);
        if(userList.size() >= pourcentage) {
            return false;
        }
        return true;
    }*/
}
