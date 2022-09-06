package com.rateplanalert.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rateplanalert.client.service.ClientService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClient(@RequestBody ClientRequest request) {
        clientService.save(request.toEntity());
    }

    @GetMapping()
    public ClientsResponse findAllClients() {
        return ClientsResponse.from(clientService.findAll());
    }
}
