package com.leave.repositories;

import com.leave.entities.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LeaveRepository extends MongoRepository<Leave, Integer> {


}
