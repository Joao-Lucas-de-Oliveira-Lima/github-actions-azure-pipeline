package edu.jl.githubactionsazureapi.application.usecase;

import edu.jl.githubactionsazureapi.application.gateway.UserRepositoryGateway;
import edu.jl.githubactionsazureapi.domain.entity.UserEntity;

public class FindUserUseCase {
    private final UserRepositoryGateway userRepositoryGateway;

    public FindUserUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public UserEntity findById(long id){
        return userRepositoryGateway.findById(id);
    }
}
