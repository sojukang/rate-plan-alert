package com.rateplanalert.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @ResponseStatus(HttpStatus.OK)
    public ClientsResponse findAllClients() {
        return ClientsResponse.from(clientService.findAll());
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse findClient(@PathVariable Long clientId) {
        return ClientResponse.from(clientService.findById(clientId));
    }

    @PutMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClient(@PathVariable Long clientId, @RequestBody ClientUpdateRequest request) {
        clientService.update(clientId, request.toEntity());
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteById(clientId);
    }
}
