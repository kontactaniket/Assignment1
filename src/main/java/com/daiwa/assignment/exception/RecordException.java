package com.daiwa.assignment.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordException extends RuntimeException {
    private String message;
}
