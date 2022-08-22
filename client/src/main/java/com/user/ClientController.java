package com.user;

import com.feign.client.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Void> createNewUser(@RequestBody ClientDto clientDto){
        clientService.createNewClient(clientDto);
        return ResponseEntity.ok().build();
    }
    
}
