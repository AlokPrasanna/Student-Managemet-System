package com.example.studentms.repo;

import com.example.studentms.entity.TermTestTimetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TermTestTimetableRepo extends JpaRepository<TermTestTimetable,Integer> {

      boolean existsTermTestTimetableByName(String name);
      Optional<TermTestTimetable> findTermTestTimetableByName(String name);
}
