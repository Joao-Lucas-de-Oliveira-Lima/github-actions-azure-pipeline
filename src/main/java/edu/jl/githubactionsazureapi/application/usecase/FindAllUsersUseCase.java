package edu.jl.githubactionsazureapi.application.usecase;

import edu.jl.githubactionsazureapi.application.gateway.UserRepositoryGateway;
import edu.jl.githubactionsazureapi.domain.entity.UserEntity;

import java.util.List;

public class FindAllUsersUseCase {
    private final UserRepositoryGateway userRepositoryGateway;

    public FindAllUsersUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public List<UserEntity> findAll(){
        return userRepositoryGateway.findAll();
    }
}
