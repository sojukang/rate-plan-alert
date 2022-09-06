package com.rateplanalert.client.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rateplanalert.client.domain.Client;
import com.rateplanalert.client.repository.ClientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Client Not Found"));
    }

    @Transactional
    public void update(Long id, Client updatedClient) {
        Client client = findById(id);
        client.update(updatedClient);
    }
}
