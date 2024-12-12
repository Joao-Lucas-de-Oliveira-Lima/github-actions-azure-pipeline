package edu.jl.githubactionsazureapi.infrastructure.model;

import edu.jl.githubactionsazureapi.mock.UserMock;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link UserModel}
 */
@ActiveProfiles("test")
class UserModelTest extends UserMock {

    @Test
    @DisplayName("Should correctly set and get the 'id' property")
    void shouldSetAndGetId() {
        userModelForGetSetTest.setId(1L);
        assertEquals(1L, userModelForGetSetTest.getId(), "The 'id' property was not correctly set or retrieved");
    }

    @Test
    @DisplayName("Should correctly set and get the 'username' property")
    void shouldSetAndGetUsername() {
        userModelForGetSetTest.setUsername("testuserModelForGetSetTest");
        assertEquals("testuserModelForGetSetTest", userModelForGetSetTest.getUsername(), "The 'username' property was not correctly set or retrieved");
    }

    @Test
    @DisplayName("Should correctly set and get the 'fullName' property")
    void shouldSetAndGetFullName() {
        userModelForGetSetTest.setFullName("Test User");
        assertEquals("Test User", userModelForGetSetTest.getFullName(), "The 'fullName' property was not correctly set or retrieved");
    }

    @Test
    @DisplayName("Should pass equals and hashCode contract validation")
    void shouldValidateEqualsAndHashCodeContract() {
        EqualsVerifier.simple().forClass(UserModel.class).verify();
    }
}
