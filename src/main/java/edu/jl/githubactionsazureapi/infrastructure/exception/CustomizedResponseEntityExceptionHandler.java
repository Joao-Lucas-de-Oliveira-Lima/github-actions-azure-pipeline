package edu.jl.githubactionsazureapi.infrastructure.exception;

import edu.jl.githubactionsazureapi.infrastructure.dto.exception.ExceptionDto;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handlerException(WebRequest webRequest){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildExceptionDto(webRequest, new Exception("An internal server error has occurred.")));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> handlerResourceNotFoundException(WebRequest webRequest, Exception exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildExceptionDto(webRequest, exception));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDto> handlerBadRequestException(WebRequest webRequest, Exception exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildExceptionDto(webRequest, exception));
    }

    private ExceptionDto buildExceptionDto(WebRequest webRequest, Exception exception){
        return new ExceptionDto(
                exception.getMessage(),
                webRequest.getDescription(false),
                new Date());
    }
}
