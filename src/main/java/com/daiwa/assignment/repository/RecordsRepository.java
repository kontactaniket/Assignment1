package com.daiwa.assignment.repository;

import com.daiwa.assignment.entity.Genre;
import com.daiwa.assignment.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordsRepository extends JpaRepository<Record, Integer> {

    Optional<Record> getByName(String name);

    Optional<List<Record>> getByGenre(Genre genre);
}
