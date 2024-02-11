package com.esra.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    int code;
    String message;
    List<String> fields;
    @Builder.Default
    private LocalDateTime date = LocalDateTime.now();

}