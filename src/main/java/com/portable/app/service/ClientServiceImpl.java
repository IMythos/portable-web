package com.portable.app.service;

import com.portable.app.entity.Client;
import com.portable.app.interfaces.IClientService;
import com.portable.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Client> listClients() {
        return clientRepository.listClients();
    }

    @Override
    @Transactional
    public Client createClient(Client client) {
        return clientRepository.createClient(
            client.getFirstname(),
            client.getPaternalLastname(),
            client.getMaternalLastname(),
            client.getRucDni(),
            client.getAddress(),
            client.getPhone(),
            client.getEmail()
        );
    }

    @Override
    @Transactional
    public void updateClient(Client client) {
        clientRepository.updateClient(
            client.getClientId(),
            client.getFirstname(),
            client.getPaternalLastname(),
            client.getMaternalLastname(),
            client.getRucDni(),
            client.getAddress(),
            client.getPhone(),
            client.getEmail()
        );
    }

    @Override
    @Transactional
    public void deleteClient(Integer clientId) {
        clientRepository.deleteClient(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalClients() {
        return (int) clientRepository.count();
    }

    @Override
    @Transactional
    public Client findClientById(Integer clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }
}