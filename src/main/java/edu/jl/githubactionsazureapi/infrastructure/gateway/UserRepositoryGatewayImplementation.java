package edu.jl.githubactionsazureapi.infrastructure.gateway;

import edu.jl.githubactionsazureapi.application.gateway.UserRepositoryGateway;
import edu.jl.githubactionsazureapi.domain.entity.UserEntity;
import edu.jl.githubactionsazureapi.infrastructure.exception.ResourceNotFoundException;
import edu.jl.githubactionsazureapi.infrastructure.model.UserModel;
import edu.jl.githubactionsazureapi.infrastructure.repository.UserRepository;
import edu.jl.githubactionsazureapi.shared.mapper.Mapper;

import java.util.List;

public class UserRepositoryGatewayImplementation implements UserRepositoryGateway {

    private final UserRepository userRepository;
    private final Mapper mapper;

    public UserRepositoryGatewayImplementation(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity findById(long id) throws ResourceNotFoundException {
        UserModel userFound = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with ID %s not found!", id)));
        return mapper.mapToObject(userFound, UserEntity.class);
    }

    @Override
    public UserEntity save(UserEntity user) {
        UserModel userAsModel = mapper.mapToObject(user, UserModel.class);
        UserModel userSaved = userRepository.save(userAsModel);
        return mapper.mapToObject(userSaved, UserEntity.class);
    }

    @Override
    public UserEntity update(long id, UserEntity update) {
        UserModel userFound = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with ID %s not found!", id)));
        mapper.mapProperties(update, userFound);
        UserModel userUpdated = userRepository.save(userFound);
        return mapper.mapToObject(userUpdated, UserEntity.class);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userModel -> mapper.mapToObject(userModel, UserEntity.class))
                .toList();
    }
}
