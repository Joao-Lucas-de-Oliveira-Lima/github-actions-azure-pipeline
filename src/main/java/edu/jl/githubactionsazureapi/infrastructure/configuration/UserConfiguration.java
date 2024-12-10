package edu.jl.githubactionsazureapi.infrastructure.configuration;

import edu.jl.githubactionsazureapi.application.gateway.UserRepositoryGateway;
import edu.jl.githubactionsazureapi.application.usecase.*;
import edu.jl.githubactionsazureapi.infrastructure.gateway.UserRepositoryGatewayImplementation;
import edu.jl.githubactionsazureapi.infrastructure.repository.UserRepository;
import edu.jl.githubactionsazureapi.shared.mapper.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public UserRepositoryGateway createUserRepositoryGateway(UserRepository userRepository, Mapper mapper){
        return new UserRepositoryGatewayImplementation(userRepository, mapper);
    }

    @Bean
    public DeleteUserUseCase createDeleteUserUseCase(UserRepositoryGateway userRepositoryGateway){
        return new DeleteUserUseCase(userRepositoryGateway);
    }

    @Bean
    public FindAllUsersUseCase createFindAllUsersUseCase(UserRepositoryGateway userRepositoryGateway){
        return new FindAllUsersUseCase(userRepositoryGateway);
    }

    @Bean
    public FindUserUseCase createFindUserUseCase(UserRepositoryGateway userRepositoryGateway){
        return new FindUserUseCase(userRepositoryGateway);
    }


    @Bean
    public SaveUserUseCase createSaveUserUseCase(UserRepositoryGateway userRepositoryGateway){
        return new SaveUserUseCase(userRepositoryGateway);
    }

    @Bean
    public UpdateUserUseCase createUpdateUserUseCase(UserRepositoryGateway userRepositoryGateway){
        return new UpdateUserUseCase(userRepositoryGateway);
    }
}
