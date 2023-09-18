package com.daiwa.assignment.service;

import com.daiwa.assignment.dto.RecordDTO;
import com.daiwa.assignment.entity.Genre;
import com.daiwa.assignment.entity.Record;
import com.daiwa.assignment.exception.RecordException;
import com.daiwa.assignment.repository.RecordsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;
import static com.daiwa.assignment.service.RecordBuilder.buildRecordEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecodServiceTest {

    private static final String
            RECORD_CREATED = "Record Created Successfully!!!";

    @InjectMocks
    private RecordService classUnderTest;
    @Mock
    private RecordsRepository repo;

    @Test
    void _test_add_nameGenrePrice() {
        Record record = buildRecordEntity("Name1", "POP", 100.50f);
        Record mockedObject = Record.builder()
                .id(1)
                .name(record.getName())
                .genre(record.getGenre())
                .created(record.getCreated())
                .price(record.getPrice())
                .build();
        when(repo.save(any())).thenReturn(mockedObject);
        RecordDTO returnedObject = classUnderTest.add("Name1", "ROCK", 100.50f);
        assertEquals(returnedObject.getId(), 1);
        assertEquals(returnedObject.getName(), "Name1");
        assertEquals(returnedObject.getGenre(), "POP");
        assertEquals(returnedObject.getPrice(), 100.50f);
        assertEquals(returnedObject.getCreated(), record.getCreated());
        assertEquals(returnedObject.getMessage(), RECORD_CREATED);
    }

    @Test
    void _test_add_duplicateNameException() {
        RecordException ex = assertThrowsExactly(RecordException.class
                , ()->classUnderTest.add("Name1", "TEST", 100.50f));
        assertEquals(ex.getMessage(), "No enum constant com.daiwa.assignment.entity.Genre.TEST");
    }

    @Test
    void _test_getById_validId() {
        LocalDateTime dateTime = LocalDateTime.now();
        Record mockObj = Record.builder()
                            .id(1)
                                .name("Name1")
                                    .genre(Genre.POP)
                                        .price(100.50f)
                                                .created(dateTime).build();
        when(repo.findById(1)).thenReturn(Optional.of(mockObj));
        RecordDTO returnedObject = classUnderTest.getById(1);
        assertEquals(returnedObject.getId(), 1);
        assertEquals(returnedObject.getName(), "Name1");
        assertEquals(returnedObject.getGenre(), "POP");
        assertEquals(returnedObject.getPrice(), 100.50f);
        assertEquals(returnedObject.getCreated(), dateTime);
    }

    @Test
    void _test_getById_invalidId() {
        when(repo.findById(1)).thenReturn(Optional.empty());
        RecordException ex = assertThrows(RecordException.class, ()->classUnderTest.getById(1));
        assertEquals(ex.getMessage(), "No record found with id: 1");
    }
}
