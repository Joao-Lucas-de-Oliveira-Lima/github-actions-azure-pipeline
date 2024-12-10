package edu.jl.githubactionsazureapi.infrastructure.dto.exception;

import java.util.Date;

public record ExceptionDto(
        String message,
        String details,
        Date timestamp) {
}
