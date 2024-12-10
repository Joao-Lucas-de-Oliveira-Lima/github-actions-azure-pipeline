package edu.jl.githubactionsazureapi.infrastructure.configuration;

import edu.jl.githubactionsazureapi.application.gateway.UserRepositoryGateway;
import edu.jl.githubactionsazureapi.application.usecase.DeleteUserUseCase;
import edu.jl.githubactionsazureapi.application.usecase.FindAllUsersUseCase;
import edu.jl.githubactionsazureapi.application.usecase.SaveUserUseCase;
import edu.jl.githubactionsazureapi.application.usecase.UpdateUserUseCase;
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
    public FindAllUsersUseCase createFindUserUseCase(UserRepositoryGateway userRepositoryGateway){
        return new FindAllUsersUseCase(userRepositoryGateway);
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
