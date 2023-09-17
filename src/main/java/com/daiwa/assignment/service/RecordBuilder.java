package com.daiwa.assignment.service;

import com.daiwa.assignment.dto.RecordDTO;
import com.daiwa.assignment.entity.Genre;
import com.daiwa.assignment.entity.Record;
import java.time.LocalDateTime;

public class RecordBuilder {
    private static final String RECORD_CREATED = "Record Created Successfully!!!";

    public static Record buildRecordEntity(
            String name, String genre, Float price) throws RuntimeException {
        return Record.builder()
                .name(name)
                .created(LocalDateTime.now())
                .price(price)
                .genre(Genre.valueOf(genre))
                .build();
    }

    public static RecordDTO buildRecordDto(Record record) {
        return buildRecordDto(record.getId(), record.getName(), record
                .getGenre().name(), record.getPrice(), record.getCreated(), RECORD_CREATED);
    }

    public static RecordDTO buildRecordDto(Integer id,
            String name, String genre, Float price, LocalDateTime created, String message) {
        return RecordDTO.builder()
                .id(id)
                .name(name)
                .genre(genre)
                .price(price)
                .message(message)
                .created(created)
                .build();
    }
}
