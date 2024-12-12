package edu.jl.githubactionsazureapi.infrastructure.dto.user;

import edu.jl.githubactionsazureapi.mock.UserMock;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link UserRequestDto}
 */
@ActiveProfiles("test")
class UserRequestDtoTest extends UserMock {

    @Test
    @DisplayName("Should correctly set and get the 'username' property")
    void shouldSetAndGetUsername() {
        userRequestDtoForGetSetTest.setUsername("testUser");
        assertEquals("testUser", userRequestDtoForGetSetTest.getUsername(), "The 'username' property was not correctly set or retrieved");
    }

    @Test
    @DisplayName("Should correctly set and get the 'fullName' property")
    void shouldSetAndGetFullName() {
        userRequestDtoForGetSetTest.setFullName("Test User");
        assertEquals("Test User", userRequestDtoForGetSetTest.getFullName(), "The 'fullName' property was not correctly set or retrieved");
    }

    @Test
    @DisplayName("Should pass equals and hashCode contract validation")
    void shouldValidateEqualsAndHashCodeContract() {
        EqualsVerifier.simple().forClass(UserRequestDto.class).verify();
    }
}
