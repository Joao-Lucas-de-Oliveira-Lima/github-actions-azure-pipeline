package edu.jl.githubactionsazureapi.infrastructure.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(unique = true)
    private String username;
    private Boolean isActive;

    public UserModel(long id, String username, Boolean isActive) {
        this.id = id;
        this.username = username;
        this.isActive = isActive;
    }

    public UserModel() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserModel userModel = (UserModel) object;
        return id == userModel.id && Objects.equals(username, userModel.username) && Objects.equals(isActive, userModel.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, isActive);
    }
}
