package com.daiwa.assignment.controller;

import com.daiwa.assignment.dto.RecordDTO;
import com.daiwa.assignment.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.daiwa.assignment.service.RecordBuilder.buildRecordDto;

@RestController
@AllArgsConstructor
@RequestMapping("/record")
public class RecordController {

    private final RecordService service;
    private static final String
            DUPLICATE_NAME_ERROR = "Record with same name already exists!!!";

    @PostMapping("/add")
    public ResponseEntity<RecordDTO> addRecord(
            @RequestParam String name
            , @RequestParam String genre
            , @RequestParam Float price) {
        try {
            return ResponseEntity.ok(service.add(name, genre, price));
        }  catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().body(
                    buildRecordDto(null, name, genre, price, null, DUPLICATE_NAME_ERROR));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(
                    buildRecordDto(null, name, genre, price, null, ex.getMessage()));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(
                    buildRecordDto(null, name, genre, price, null, ex.getMessage()));
        }
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<RecordDTO> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.getById(id))
                    ;
        } catch (RuntimeException ex){
            return ResponseEntity.notFound()
                    .header("NOT FOUND",ex.getMessage()).build();
        }
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<RecordDTO> getByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(service.getByName(name));
        } catch (RuntimeException ex){
            return ResponseEntity.notFound()
                    .header("NOT FOUND",ex.getMessage()).build();
        }
    }

    @GetMapping("/get/genre/{genre}")
    public ResponseEntity<List<RecordDTO>> getByGenre(@PathVariable String genre) {
        try {
            return ResponseEntity.ok(service.getByGenre(genre));
        } catch (RuntimeException ex){
            return ResponseEntity.notFound()
                    .header("NOT FOUND",ex.getMessage()).build();
        }
    }
}
