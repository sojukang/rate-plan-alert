package com.rateplanalert.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.rateplanalert.client.domain.Client;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientsResponse {

    private List<ClientResponse> clients;

    public static ClientsResponse from(List<Client> clients) {
        List<ClientResponse> clientsResponse = clients.stream()
            .map(ClientResponse::from)
            .collect(Collectors.toUnmodifiableList());

        return new ClientsResponse(clientsResponse);
    }
}
