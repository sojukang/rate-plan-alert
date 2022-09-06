package com.rateplanalert.client.acceptance;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.rateplanalert.controller.ClientRequest;
import com.rateplanalert.controller.ClientResponse;
import com.rateplanalert.controller.ClientsResponse;

public class ClientAcceptanceTest extends AcceptanceTest {

    @Test
    @Deprecated
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
}
