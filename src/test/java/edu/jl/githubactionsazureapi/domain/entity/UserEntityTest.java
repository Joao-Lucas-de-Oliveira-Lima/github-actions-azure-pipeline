package edu.jl.githubactionsazureapi.domain.entity;

import edu.jl.githubactionsazureapi.mock.UserMock;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link UserEntity}
 */
@ActiveProfiles("test")
class UserEntityTest extends UserMock {

    @Test
    @DisplayName("Should correctly set and get the 'id' property")
    void shouldSetAndGetId() {
        userEntityForGetSetTest.setId(1L);
        assertEquals(1L, userEntityForGetSetTest.getId(), "The 'id' property was not correctly set or retrieved");
    }

    @Test
    @DisplayName("Should correctly set and get the 'username' property")
    void shouldSetAndGetUsername() {
        userEntityForGetSetTest.setUsername("testUser");
        assertEquals("testUser", userEntityForGetSetTest.getUsername(), "The 'username' property was not correctly set or retrieved");
    }

    @Test
    @DisplayName("Should correctly set and get the 'fullName' property")
    void shouldSetAndGetFullName() {
        userEntityForGetSetTest.setFullName("Test User");
        assertEquals("Test User", userEntityForGetSetTest.getFullName(), "The 'fullName' property was not correctly set or retrieved");
    }

    @Test
    @DisplayName("Should pass equals and hashCode contract validation")
    void shouldValidateEqualsAndHashCodeContract() {
        EqualsVerifier.simple().forClass(UserEntity.class).verify();
    }
}
