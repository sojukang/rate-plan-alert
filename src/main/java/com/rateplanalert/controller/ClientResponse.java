package com.rateplanalert.controller;

import com.rateplanalert.client.domain.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientResponse {

    private Long id;

    private String name;

    private String phoneModel;

    private String phoneNumber;

    public static ClientResponse from(Client client) {
        return new ClientResponse(client.getId(), client.getName(), client.getPhoneModel(),
            client.getPhoneNumber());
    }

}
