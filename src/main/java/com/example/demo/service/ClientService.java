package com.example.demo.service;

import com.example.demo.model.Client;
import java.io.IOException;
import java.util.Collection;

//  Interface to list methods for clients.
//  Methods are implemented in service.implementation.ClientServiceImplementation.java.

public interface ClientService {
    //  Create and return a client to add to database.
    Client create(Client client);
    Client ping(String clientCompanyURL) throws IOException;
    //  List to contain clients returned. Limit: limit on number of clients returned.
    Collection<Client> clientList(int limit);
    //  Return client by id from the database.
    Client get(Long clientId);
    //  Update client in the database.
    Client update(Client client);
    //  Delete client from the database.
    Boolean delete(Long clientId);
}
