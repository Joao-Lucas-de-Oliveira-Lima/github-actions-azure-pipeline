package edu.jl.githubactionsazureapi.infrastructure.dto.user;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UserResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String fullName;

    public UserResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserResponseDto that = (UserResponseDto) object;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, fullName);
    }
}
