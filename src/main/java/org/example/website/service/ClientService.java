package org.example.website.service;

import org.example.website.model.Client;
import org.example.website.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(String name, Long number,String text) {
       Client client = new Client();
       client.setName(name);
       client.setNumber(number);
       client.setText(text);
       clientRepository.save(client);

    }
}
