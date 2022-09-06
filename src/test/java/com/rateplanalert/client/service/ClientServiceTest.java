package com.rateplanalert.client.service;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.rateplanalert.client.domain.Client;

@SpringBootTest
@Transactional
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    @DisplayName("고객의 정보를 등록한다.")
    void save() {
        // given
        Client client = new Client("강승주", "iphone mini 12", "010-0999-4482");

        // when
        Client savedClient = clientService.save(client);

        // then
        assertThat(savedClient.getName()).isEqualTo(client.getName());
    }
}
