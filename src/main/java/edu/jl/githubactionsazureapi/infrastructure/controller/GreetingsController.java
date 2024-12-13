package edu.jl.githubactionsazureapi.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping
    public ResponseEntity<GreetingsResponseDto> getGreetings() {
        String message = "Hello updated! If you are seeing this message, it means the server on the Azure VM is still running!";
        return ResponseEntity.ok(new GreetingsResponseDto(message));
    }

    public record GreetingsResponseDto(String message) {
    }
}