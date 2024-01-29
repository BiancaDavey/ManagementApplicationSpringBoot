package com.example.demo.service;

import com.example.demo.model.Server;
import java.util.Collection;
import java.io.IOException;

//  Interface to list methods for servers.
//  Methods are implemented in service.implementation.ServerServiceImplementation.java.

public interface ServerService {
    //  Create and return a server to add to database.
    Server create(Server server);
    Server ping(String serverIPAddress) throws IOException;
    //  List to contain servers returned. Limit: limit on number of Servers returned.
    Collection<Server> serverList(int limit);
    //  Return server by id from the database.
    Server get(Long serverId);
    //  Update server in the database.
    Server update(Server server);
    //  Delete server from the database.
    Boolean delete(Long serverId);
}
