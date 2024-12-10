package edu.jl.githubactionsazureapi.infrastructure.dto.user;

import java.io.Serializable;
import java.util.Objects;

public class UserResponseDto implements Serializable {
    private long id;
    private String username;
    private Boolean isActive;

    public UserResponseDto() {
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
        UserResponseDto that = (UserResponseDto) object;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, isActive);
    }
}
