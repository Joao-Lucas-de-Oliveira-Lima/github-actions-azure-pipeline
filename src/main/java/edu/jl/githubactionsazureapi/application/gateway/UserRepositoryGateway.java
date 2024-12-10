package edu.jl.githubactionsazureapi.application.gateway;

import edu.jl.githubactionsazureapi.domain.entity.UserEntity;

import java.util.List;

public interface UserRepositoryGateway {
    void deleteById(long id);
    UserEntity findById(long id);
    UserEntity save(UserEntity user);
    UserEntity update(long id, UserEntity update);
    List<UserEntity> findAll();
}
