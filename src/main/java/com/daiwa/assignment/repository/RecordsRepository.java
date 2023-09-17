package com.daiwa.assignment.repository;

import com.daiwa.assignment.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordsRepository extends JpaRepository<Record, Integer> {
}
