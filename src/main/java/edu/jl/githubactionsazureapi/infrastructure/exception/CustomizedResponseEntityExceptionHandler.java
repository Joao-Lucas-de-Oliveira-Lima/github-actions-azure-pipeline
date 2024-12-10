package edu.jl.githubactionsazureapi.infrastructure.exception;

import edu.jl.githubactionsazureapi.infrastructure.dto.exception.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handlerException(WebRequest webRequest, Exception exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildExceptionDto(webRequest, exception));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> handlerResourceNotFoundException(WebRequest webRequest, Exception exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildExceptionDto(webRequest, exception));
    }

    private ExceptionDto buildExceptionDto(WebRequest webRequest, Exception exception){
        return new ExceptionDto(
                exception.getMessage(),
                webRequest.getDescription(false),
                new Date());
    }
}
