package com.learn.part7;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import jakarta.json.Json;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MobileResourceTest {

    @Test
    @Order(1)
    @TestSecurity(authorizationEnabled = false)
    void addMobile() {
        var jsonObject = Json.createObjectBuilder()
                .add("number", "s23")
                .add("Name","samsung")
                .build();
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .post("/keycloak/mobile")
                .then()
                .log().all()
                .statusCode(Response.Status.CREATED.getStatusCode());

    }

    @Test
    @Order(2)
    @TestSecurity(user = "test", roles = "student")
    void getMobileList() {
        RestAssured.given()
                .get("/keycloak/mobile")
                .then()
                .log().all()
                .body("size()", Matchers.equalTo(1));
//                .body("number", Matchers.equalTo("s23"));
    }

    @Test
    void updateMobile() {
    }

    @Test
    void deleteMobile() {
    }
}