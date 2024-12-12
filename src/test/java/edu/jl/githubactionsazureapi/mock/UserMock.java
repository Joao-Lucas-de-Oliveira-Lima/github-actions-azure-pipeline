package edu.jl.githubactionsazureapi.mock;

import edu.jl.githubactionsazureapi.domain.entity.UserEntity;
import edu.jl.githubactionsazureapi.infrastructure.dto.user.UserRequestDto;
import edu.jl.githubactionsazureapi.infrastructure.dto.user.UserResponseDto;
import edu.jl.githubactionsazureapi.infrastructure.model.UserModel;

public abstract class UserMock {

    // Mock objects for testing getter and setter methods of respective classes
    protected UserModel userModelForGetSetTest = new UserModel();
    protected UserEntity userEntityForGetSetTest = new UserEntity();
    protected UserRequestDto userRequestDtoForGetSetTest = new UserRequestDto();
    protected UserResponseDto userResponseDtoForGetSetTest = new UserResponseDto();

    protected Integer totalDatabaseUsers = 10;

    protected Long validUserId = 1L;

    protected Long invalidUserId = -1L;

    protected UserResponseDto validUserResponseDto = new
            UserResponseDto(validUserId, "john_doe", "John Doe");
    protected UserRequestDto validUserRequestDto =
            new UserRequestDto("lucas_silva", "Lucas Pereira Silva");

    protected UserRequestDto invalidUserRequestDtoWithNullUsername =
            new UserRequestDto(null, "Lucas Pereira Silva");

    protected UserRequestDto invalidUserRequestDtoWithEmptyUsername =
            new UserRequestDto("", "Lucas Pereira Silva");

    protected UserRequestDto invalidUserRequestDtoWithBlankUsername =
            new UserRequestDto("    ", "Lucas Pereira Silva");

    protected UserRequestDto invalidUserRequestDtoWithNullFullName =
            new UserRequestDto("lucas_silva", null);

    protected UserRequestDto invalidUserRequestDtoWithBlankFullName =
            new UserRequestDto("lucas_silva", "      ");

    protected UserRequestDto invalidUserRequestDtoWithEmptyFullName =
            new UserRequestDto("lucas_silva", "");
}
