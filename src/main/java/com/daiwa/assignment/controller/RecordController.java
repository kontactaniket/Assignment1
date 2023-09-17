package com.daiwa.assignment.controller;

import com.daiwa.assignment.dto.RecordDTO;
import com.daiwa.assignment.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.daiwa.assignment.service.RecordBuilder.buildRecordDto;

@RestController
@AllArgsConstructor
@RequestMapping("/record")
public class RecordController {

    private final RecordService service;

    @PostMapping("/add")
    public ResponseEntity<RecordDTO> addRecord(@RequestParam String name
            , @RequestParam String genre, @RequestParam Float price) {
        try {
            return ResponseEntity.ok(service.add(name, genre, price));
        }  catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().body(
                    buildRecordDto(null, name, genre, price, null, ex.getMessage()));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(
                    buildRecordDto(null, name, genre, price, null, ex.getMessage()));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(
                    buildRecordDto(null, name, genre, price, null, ex.getMessage()));
        }
    }
}
