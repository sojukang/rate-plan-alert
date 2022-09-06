package com.rateplanalert.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rateplanalert.client.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
