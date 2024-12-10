package edu.jl.githubactionsazureapi.infrastructure.controller;

import edu.jl.githubactionsazureapi.application.usecase.*;
import edu.jl.githubactionsazureapi.domain.entity.UserEntity;
import edu.jl.githubactionsazureapi.infrastructure.dto.user.UserRequestDto;
import edu.jl.githubactionsazureapi.infrastructure.dto.user.UserResponseDto;
import edu.jl.githubactionsazureapi.shared.mapper.Mapper;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final DeleteUserUseCase deleteUserUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final FindUserUseCase findUserUseCase;
    private final SaveUserUseCase saveUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final Mapper mapper;

    @Autowired
    public UserController(
            DeleteUserUseCase deleteUserUseCase,
            FindAllUsersUseCase findAllUsersUseCase,
            FindUserUseCase findUserUseCase,
            SaveUserUseCase saveUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            Mapper mapper) {
        this.deleteUserUseCase = deleteUserUseCase;
        this.findAllUsersUseCase = findAllUsersUseCase;
        this.findUserUseCase = findUserUseCase;
        this.saveUserUseCase = saveUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.mapper = mapper;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> save(
            @Valid @RequestBody UserRequestDto requestBody,
            BindingResult requestValidationErrors) throws BadRequestException {

        verifyErrorsInRequest(requestValidationErrors);

        UserEntity userAsEntity = mapper.mapToObject(requestBody, UserEntity.class);
        UserEntity userSaved = saveUserUseCase.save(userAsEntity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.mapToObject(userSaved, UserResponseDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable(name = "id") Long id) {
        UserEntity userFound = findUserUseCase.findById(id);
        return ResponseEntity.ok(mapper.mapToObject(userFound, UserResponseDto.class));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserEntity> userEntityList = findAllUsersUseCase.findAll();
        List<UserResponseDto> userResponseDtoList = userEntityList
                .stream()
                .map(userEntity -> mapper.mapToObject(userEntity, UserResponseDto.class))
                .toList();
        return ResponseEntity.ok(userResponseDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
        deleteUserUseCase.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateById(
            @PathVariable("id") Long userId,
            @RequestBody @Valid UserRequestDto userRequestDto,
            BindingResult requestValidationErrors) throws BadRequestException {

        verifyErrorsInRequest(requestValidationErrors);

        UserEntity userToUpdate = mapper.mapToObject(userRequestDto, UserEntity.class);
        UserEntity updatedUser = updateUserUseCase.update(userId, userToUpdate);
        UserResponseDto updatedUserDto = mapper.mapToObject(updatedUser, UserResponseDto.class);

        return ResponseEntity.ok(updatedUserDto);
    }

    private void verifyErrorsInRequest(BindingResult validationErrorsRequest) throws BadRequestException {
        if (validationErrorsRequest.hasErrors()) {
            String errorMessage = "Errors found: ";
            errorMessage += validationErrorsRequest.getAllErrors()
                    .stream()
                    .map(error -> String.format("%s",error.getDefaultMessage()))
                    .collect(Collectors.joining(" \\ "));
            throw new BadRequestException(errorMessage);
        }
    }
}
