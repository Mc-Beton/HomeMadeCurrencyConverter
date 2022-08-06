package com.feign.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientDto {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
}
