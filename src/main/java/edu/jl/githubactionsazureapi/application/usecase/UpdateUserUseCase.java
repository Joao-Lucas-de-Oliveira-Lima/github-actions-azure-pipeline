package edu.jl.githubactionsazureapi.application.usecase;

import edu.jl.githubactionsazureapi.application.gateway.UserRepositoryGateway;
import edu.jl.githubactionsazureapi.domain.entity.UserEntity;

public class UpdateUserUseCase {
    private final UserRepositoryGateway userRepositoryGatewayGateway;

    public UpdateUserUseCase(UserRepositoryGateway userRepositoryGatewayGateway) {
        this.userRepositoryGatewayGateway = userRepositoryGatewayGateway;
    }
    
    public UserEntity update(long id, UserEntity update){
        return userRepositoryGatewayGateway.update(id, update);
    }
}
