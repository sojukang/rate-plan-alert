package com.rateplanalert.client.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rateplanalert.client.domain.Client;
import com.rateplanalert.client.repository.ClientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
