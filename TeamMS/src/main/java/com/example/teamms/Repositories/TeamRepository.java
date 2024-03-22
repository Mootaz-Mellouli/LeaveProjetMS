package com.example.teamms.Repositories;

import com.example.teamms.Entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer>{
        @Query("select t from Team t where t.archive = false")
        List<Team> findByArchiveIsFalse();
}