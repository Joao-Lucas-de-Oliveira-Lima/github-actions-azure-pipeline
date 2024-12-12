package edu.jl.githubactionsazureapi.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
/* Integration test for Domain and Application layers, ensuring security isolation
 * for external layers, following clean architecture principles */
class ArchitectureIT {

    @LocalServerPort
    private int port;

    private final JavaClasses javaClasses = new ClassFileImporter()
            .importPackages("edu.jl.githubactionsazureapi");

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Verify that classes in the Domain package do not depend on Application, Infrastructure, or external packages")
    void verifyRestrictionsForDomainPackage() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .and().haveSimpleNameNotContaining("Test")
                .and().haveSimpleNameNotContaining("IT")
                .should().dependOnClassesThat()
                .resideInAnyPackage(
                        "..application..",
                        "..infrastructure..",
                        "..shared.mapper.implementation..",
                        "org..",
                        "jakarta..");
        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Verify that classes in the Application package do not depend on Infrastructure, external packages, or shared mapper implementation")
    void verifyRestrictionsForApplicationPackage() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..application..")
                .and().haveSimpleNameNotContaining("Test")
                .and().haveSimpleNameNotContaining("IT")
                .should().dependOnClassesThat()
                .resideInAnyPackage(
                        "..infrastructure..",
                        "org..",
                        "jakarta..",
                        "..shared.mapper.implementation..");
        rule.check(javaClasses);
    }
}
