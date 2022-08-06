package com.user;

import com.feign.client.ClientDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientMapper {

    public Client mapToClient(ClientDto clientDto){
        return new Client(
                clientDto.getId(),
                clientDto.getName(),
                clientDto.getSurname(),
                clientDto.getEmail(),
                clientDto.getPhoneNumber()
        );
    }
    public ClientDto mapToClientDto(Client client){
        return new ClientDto(
                client.getId(),
                client.getName(),
                client.getSurname(),
                client.getEmail(),
                client.getPhoneNumber()
        );
    }
    private List<ClientDto> mapToClientDtoList(List<Client> list){
        return list.stream()
                .map(this::mapToClientDto)
                .collect(Collectors.toList());
    }
}
