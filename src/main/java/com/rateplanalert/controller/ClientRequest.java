package com.rateplanalert.controller;

import com.rateplanalert.client.domain.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientRequest {

    private String name;

    private String phoneModel;

    private String phoneNumber;

    public Client toEntity() {
        return new Client(name, phoneModel, phoneNumber);
    }
}
