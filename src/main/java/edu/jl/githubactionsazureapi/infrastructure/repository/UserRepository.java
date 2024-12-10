package edu.jl.githubactionsazureapi.infrastructure.repository;

import edu.jl.githubactionsazureapi.infrastructure.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
