package com.rateplanalert.controller;

import com.rateplanalert.client.domain.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientUpdateRequest {

    private String name;

    private String phoneModel;

    private String phoneNumber;

    public Client toEntity() {
        return new Client(name, phoneModel, phoneNumber);
    }
}
