package com.backfcdev.customersapirest.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ValidationError {
    private String code;
    private String message;
}