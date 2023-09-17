package com.daiwa.assignment.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class RecordDTO {
    private Integer id;
    private LocalDateTime created;
    private String name;
    private Float price;
    private String genre;
    private String message;
}