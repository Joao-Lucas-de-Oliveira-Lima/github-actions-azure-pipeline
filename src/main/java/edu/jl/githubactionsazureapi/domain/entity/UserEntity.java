package edu.jl.githubactionsazureapi.domain.entity;

import java.util.Objects;

public class UserEntity {
    private final long id;
    private String username;
    private Boolean isActive;

    public UserEntity(long id, String username, Boolean isActive) {
        this.id = id;
        this.username = username;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
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
        UserEntity user = (UserEntity) object;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(isActive, user.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, isActive);
    }
}
