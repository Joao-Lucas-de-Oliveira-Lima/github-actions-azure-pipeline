package edu.jl.githubactionsazureapi.infrastructure.dto.user;

import edu.jl.githubactionsazureapi.mock.UserMock;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link UserResponseDto}
 */
@ActiveProfiles("test")
class UserResponseDtoTest extends UserMock {

    @Test
    @DisplayName("Should correctly set and get the 'id' property")
    void shouldSetAndGetId() {
        userResponseDtoForGetSetTest.setId(1L);
        assertEquals(1L, userResponseDtoForGetSetTest.getId(), "The 'id' property was not correctly set or retrieved");
    }

    @Test
    @DisplayName("Should correctly set and get the 'username' property")
    void shouldSetAndGetUsername() {
        userResponseDtoForGetSetTest.setUsername("testUser");
        assertEquals("testUser", userResponseDtoForGetSetTest.getUsername(), "The 'username' property was not correctly set or retrieved");
    }

    @Test
    @DisplayName("Should correctly set and get the 'fullName' property")
    void shouldSetAndGetFullName() {
        userResponseDtoForGetSetTest.setFullName("Test User");
        assertEquals("Test User", userResponseDtoForGetSetTest.getFullName(), "The 'fullName' property was not correctly set or retrieved");
    }

    @Test
    @DisplayName("Should pass equals and hashCode contract validation")
    void shouldValidateEqualsAndHashCodeContract() {
        EqualsVerifier.simple().forClass(UserResponseDto.class).verify();
    }
}
