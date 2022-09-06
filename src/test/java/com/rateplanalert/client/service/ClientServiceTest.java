package com.rateplanalert.client.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rateplanalert.client.domain.Client;
import com.rateplanalert.controller.NotFoundException;

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

    @Test
    @Transactional(propagation = Propagation.NEVER)
    @DisplayName("모든 고객의 정보를 조회한다.")
    void findAll() {
        // given
        Client client1 = new Client("강승주", "iphone mini 12", "010-0999-4482");
        clientService.save(client1);

        Client client2 = new Client("sojukang", "Galaxy note 8", "010-0999-4483");
        clientService.save(client2);

        // when
        List<Client> actual = clientService.findAll();

        // then
        assertAll(
            () -> assertThat(actual).hasSize(2),
            () -> assertThat(actual.get(0).getName()).isEqualTo("강승주"),
            () -> assertThat(actual.get(1).getName()).isEqualTo("sojukang")
        );
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    @DisplayName("고객의 정보를 수정한다.")
    void update() {
        // given
        Client client = new Client("강승주", "iphone mini 12", "010-0999-4482");
        clientService.save(client);

        Client updatedClient = new Client("updated name", "updated phone model", "updated phone number");

        // when
        clientService.update(client.getId(), updatedClient);
        Client actual = clientService.findById(client.getId());

        // then
        assertAll(
            () -> assertThat(actual.getName()).isEqualTo(updatedClient.getName()),
            () -> assertThat(actual.getPhoneModel()).isEqualTo(updatedClient.getPhoneModel()),
            () -> assertThat(actual.getPhoneNumber()).isEqualTo(updatedClient.getPhoneNumber())
        );
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    @DisplayName("고객의 정보를 삭제한다.")
    void delete() {
        // given
        Client client = new Client("강승주", "iphone mini 12", "010-0999-4482");
        clientService.save(client);

        // when
        clientService.deleteById(client.getId());

        // then
        assertThatThrownBy(() -> clientService.findById(client.getId()))
            .isInstanceOf(NotFoundException.class)
            .hasMessageContaining("Client Not Found");
    }
}
