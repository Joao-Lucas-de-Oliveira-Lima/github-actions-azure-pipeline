package edu.jl.githubactionsazureapi.application.usecase;

import edu.jl.githubactionsazureapi.application.gateway.UserRepositoryGateway;
import edu.jl.githubactionsazureapi.domain.entity.UserEntity;

public class SaveUserUseCase {
    private final UserRepositoryGateway userRepositoryGateway;

    public SaveUserUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public UserEntity save(UserEntity user){
        return userRepositoryGateway.save(user);
    }
}
