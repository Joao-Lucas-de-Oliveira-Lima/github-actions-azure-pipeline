package edu.jl.githubactionsazureapi.infrastructure.dto.user;

import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UserRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotBlank(message = "The 'username' field must not be empty or blank.")
    private String username;

    @NotBlank(message = "The 'fullName' field must not be empty or blank.")
    private String fullName;


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
        UserRequestDto that = (UserRequestDto) object;
        return Objects.equals(username, that.username) && Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, fullName);
    }
}
