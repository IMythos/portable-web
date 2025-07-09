package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.entity.Client;

public interface IClientService {
    List<Client> listClients();
    Client createClient(Client client);
    void updateClient(Client client);
    void deleteClient(Integer clientId);
    Integer getTotalClients();
    Client findClientById(Integer clientId);
}
