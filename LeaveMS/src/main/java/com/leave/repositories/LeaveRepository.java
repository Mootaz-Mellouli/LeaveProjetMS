package com.leave.repositories;

import com.leave.entities.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LeaveRepository extends MongoRepository<Leave, Integer> {

    //List<Leave> getLeavesByIsArchivedIsFalse();
    //List<Leave> getLeavesByIsArchivedIsTrue();

    List<Leave> findByArchivedIsFalse();
    List<Leave> findByArchivedIsTrue();
}
