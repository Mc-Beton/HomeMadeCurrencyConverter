package com.user;


import com.feign.client.ClientDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;

    public void createNewClient(ClientDto clientDto){
        clientRepository.save(clientMapper.mapToClient(clientDto));
    }
}
