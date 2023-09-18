package com.daiwa.assignment.service;

import com.daiwa.assignment.dto.RecordDTO;
import com.daiwa.assignment.entity.Genre;
import com.daiwa.assignment.entity.Record;
import com.daiwa.assignment.repository.RecordsRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.daiwa.assignment.service.RecordBuilder.buildRecordDto;
import static com.daiwa.assignment.service.RecordBuilder.buildRecordEntity;

@Component
@AllArgsConstructor
public class RecordService {

    private final RecordsRepository repository;
    public RecordDTO add(String name, String genre, Float price) {
        Record record;
        try {
            record = buildRecordEntity(name, genre, price);
        } catch (RuntimeException ex) {
            //LOG
            throw ex;
        }
        try {
            return buildRecordDto(repository.save(record));
        } catch (DataIntegrityViolationException ex) {
            //LOG
            throw ex;
        } catch (IllegalArgumentException ex) {
            //LOG
            throw ex;
        }
    }

    public RecordDTO getById(Integer id) throws RuntimeException {
        Optional<Record> result = repository.findById(id);
        Record record = result.orElseThrow(
                ()->{
                    //LOG
                    return new RuntimeException(String.format(
                            "No record found with id: %S", id));
                });
        return buildRecordDto(record);
    }

    public RecordDTO getByName(String name) throws RuntimeException {
        Optional<Record> result = repository.getByName(name);
        Record record = result.orElseThrow(
                ()->{
                    //LOG
                    return new RuntimeException(String.format(
                            "No record found with Name: %S", name));
                });
        return buildRecordDto(record);
    }

    public List<RecordDTO> getByGenre(String genre) throws RuntimeException {
        Optional<List<Record>> result = null;
        List<RecordDTO> dtoList = new ArrayList<>();
        try {
            result = repository.getByGenre(Genre.valueOf(genre));
        } catch (RuntimeException ex){
            throw ex;
        }
        List<Record> records = result.orElseThrow(
                ()->{
                    //LOG
                    return new RuntimeException(String.format(
                            "No record found with Name: %S", genre));
                });
        records.forEach(record->{
            dtoList.add(buildRecordDto(record));
        });
        return dtoList;
    }
}
