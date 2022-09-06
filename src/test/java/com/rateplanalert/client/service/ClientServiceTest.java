package com.rateplanalert.client.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
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

    @Test
    @Transactional(propagation = Propagation.NEVER)
    @DisplayName("Id로 고객의 정보를 조회한다.")
    void findById() {
        // given
        Client client = new Client("강승주", "iphone mini 12", "010-0999-4482");
        Client savedClient = clientService.save(client);

        // when
        Client actual = clientService.findById(savedClient.getId());

        // then
        assertThat(actual.getName()).isEqualTo("강승주");
    }
}
