package com.rateplanalert.client.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneModel;

    private String phoneNumber;

    public Client(String name, String phoneModel, String phoneNumber) {
        this.name = name;
        this.phoneModel = phoneModel;
        this.phoneNumber = phoneNumber;
    }
}
