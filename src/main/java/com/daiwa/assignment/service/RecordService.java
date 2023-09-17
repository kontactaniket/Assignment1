package com.daiwa.assignment.service;

import com.daiwa.assignment.dto.RecordDTO;
import com.daiwa.assignment.entity.Record;
import com.daiwa.assignment.repository.RecordsRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import static com.daiwa.assignment.service.RecordBuilder.buildRecordDto;
import static com.daiwa.assignment.service.RecordBuilder.buildRecordEntity;

@Component
@AllArgsConstructor
public class RecordService {
    private static final String DUPLICATE_NAME_ERROR = "Record with same name already exists!!!";
    private final RecordsRepository repository;

    public RecordDTO add(String name, String genre, Float price){
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
            return buildRecordDto(null
                    , name, genre, price, null, DUPLICATE_NAME_ERROR);
        } catch (IllegalArgumentException ex) {
            //LOG
            throw ex;
        }
    }
}
