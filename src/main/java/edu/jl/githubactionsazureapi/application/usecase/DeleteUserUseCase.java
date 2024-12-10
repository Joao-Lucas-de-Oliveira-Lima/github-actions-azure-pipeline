package edu.jl.githubactionsazureapi.application.usecase;

import edu.jl.githubactionsazureapi.application.gateway.UserRepositoryGateway;

public class DeleteUserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    public DeleteUserUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public void deleteById(long id){
        userRepositoryGateway.deleteById(id);
    }
}
