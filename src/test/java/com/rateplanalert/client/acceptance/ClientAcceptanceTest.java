package com.rateplanalert.client.acceptance;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.rateplanalert.controller.ClientRequest;
import com.rateplanalert.controller.ClientResponse;
import com.rateplanalert.controller.ClientUpdateRequest;
import com.rateplanalert.controller.ClientsResponse;

public class ClientAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("고객의 정보를 등록한다.")
    void saveClient() {
        // when
        ClientRequest request = new ClientRequest("강승주", "iphone mini 12", "010-0999-4482");
        post("/api/clients", request)
            .statusCode(HttpStatus.CREATED.value());

        // then
        List<ClientResponse> clients = get("/api/clients")
            .statusCode(HttpStatus.OK.value())
            .extract()
            .body()
            .jsonPath().getObject(".", ClientsResponse.class)
            .getClients();

        Assertions.assertAll(
            () -> assertThat(clients).hasSize(1),
            () -> assertThat(clients.get(0).getName()).isEqualTo("강승주")
        );
    }

    @Test
    @DisplayName("고객의 정보를 조회한다.")
    void findClient() {
        // given
        ClientRequest request = new ClientRequest("강승주", "iphone mini 12", "010-0999-4482");
        post("/api/clients", request)
            .statusCode(HttpStatus.CREATED.value());

        // when, then
        get("/api/clients/1")
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("name", equalTo("강승주"));
    }

    @Test
    @DisplayName("모든 고객의 정보를 조회한다.")
    void findAllClient() {
        // when
        ClientRequest request1 = new ClientRequest("강승주", "iphone mini 12", "010-0999-4482");
        post("/api/clients", request1)
            .statusCode(HttpStatus.CREATED.value());

        ClientRequest request2 = new ClientRequest("sojukang", "Galaxy note 8", "010-0999-4483");
        post("/api/clients", request2)
            .statusCode(HttpStatus.CREATED.value());

        // then
        List<ClientResponse> clients = get("/api/clients")
            .statusCode(HttpStatus.OK.value())
            .extract()
            .body()
            .jsonPath().getObject(".", ClientsResponse.class)
            .getClients();

        Assertions.assertAll(
            () -> assertThat(clients).hasSize(2),
            () -> assertThat(clients.get(0).getName()).isEqualTo("강승주"),
            () -> assertThat(clients.get(1).getName()).isEqualTo("sojukang")
        );
    }

    @Test
    @DisplayName("고객의 정보를 수정한다.")
    void update() {
        // given
        ClientRequest request = new ClientRequest("강승주", "iphone mini 12", "010-0999-4482");
        post("/api/clients", request)
            .statusCode(HttpStatus.CREATED.value());

        // when
        ClientUpdateRequest updateRequest = new ClientUpdateRequest("updated name", "updated phoneModel",
            "updated phoneNumber");
        put("/api/clients/1", updateRequest)
            .statusCode(HttpStatus.NO_CONTENT.value());

        // then
        get("/api/clients/1")
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("name", equalTo("updated name"))
            .body("phoneModel", equalTo("updated phoneModel"))
            .body("phoneNumber", equalTo("updated phoneNumber"));
    }

    @Test
    @DisplayName("고객의 정보를 삭제한다.")
    void delete() {
        // given
        ClientRequest request = new ClientRequest("강승주", "iphone mini 12", "010-0999-4482");
        post("/api/clients", request)
            .statusCode(HttpStatus.CREATED.value());

        // when
        delete("/api/clients/1")
            .statusCode(HttpStatus.NO_CONTENT.value());

        // then
        get("/api/clients/1")
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
