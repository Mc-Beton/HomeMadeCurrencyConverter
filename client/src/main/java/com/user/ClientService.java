package com.user;


import com.feign.client.ClientDto;
import com.feign.client.ClientType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ClientService {


    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private void changeClientTypeForDevsOnly(Client client, ClientType clientType){
        clientRepository.save(
                new Client(
                        client.getId(),
                        client.getName(),
                        client.getSurname(),
                        client.getEmail(),
                        client.getPhoneNumber(),
                        clientType
                )
        );
    }

    public void createNewClient(ClientDto clientDto){
        log.info("Process started: add new client account...  ");
        Optional<Client> byEmail = clientRepository.findByEmail(clientDto.getEmail());
        Optional<Client> byPhoneNumer = clientRepository.findByPhoneNumber(clientDto.getPhoneNumber());
        if (!byEmail.isPresent()){
            if (!byPhoneNumer.isPresent()){
                clientRepository.save(clientMapper.mapToClient(clientDto));
            }
        }else{
            log.error("Email or phone number already in use!");
        }
    }
    public void changeClientType(Integer id, String clientType){
        log.info("Process started: change client account type...");
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()){
            if (clientType.equalsIgnoreCase(ClientType.BASIC.toString())){
                changeClientTypeForDevsOnly(byId.get(),ClientType.BASIC);
            } else if (clientType.equalsIgnoreCase(ClientType.NORMAL.toString())) {
                changeClientTypeForDevsOnly(byId.get(),ClientType.NORMAL);
            }else if (clientType.equalsIgnoreCase(ClientType.PREMIUM.toString())){
                changeClientTypeForDevsOnly(byId.get(),ClientType.PREMIUM);
            }else{
                log.error("Wrong client account type");
            }
        }else{
            log.error("Client with given id, not found!");
        }
    }

}
