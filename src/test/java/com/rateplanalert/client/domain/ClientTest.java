package com.rateplanalert.client.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    @DisplayName("고객의 정보를 수정한다.")
    void update() {
        // given
        Client client = new Client("강승주", "iphone mini 12", "010-0999-4482");

        // when
        Client updatedClient = new Client("updated name", "updated phone model", "updated phone number");
        client.update(updatedClient);

        // then
        assertAll(
            () -> assertThat(client.getName()).isEqualTo(updatedClient.getName()),
            () -> assertThat(client.getPhoneModel()).isEqualTo(updatedClient.getPhoneModel()),
            () -> assertThat(client.getPhoneNumber()).isEqualTo(updatedClient.getPhoneNumber())
        );
    }
}
