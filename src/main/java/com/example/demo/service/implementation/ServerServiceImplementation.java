package com.example.demo.service.implementation;

import com.example.demo.model.Server;
import com.example.demo.repo.ServerRepo;
import com.example.demo.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static com.example.demo.enumeration.Status.SERVER_DOWN;
import static com.example.demo.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

//  Implements the methods in the ServerService interface.
//@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {
    private final ServerRepo serverRepo;

    //  TODO: added in. Annotation didn't replace constructor.
    public ServerServiceImplementation(ServerRepo serverRepo) {
        this.serverRepo = serverRepo;
    }

    @Override
    public Server create(Server server) {
        System.out.println("Saving new server: " + server.getClass().getName());
        // log.info("Saving new server: {}", server.getName());
        //  Call function to set image url for the server.
        // TODO: error without Server.java method added in.
        server.setServerImageUrl(setServerImageUrl());
        //  Save server in the database.
        return serverRepo.save(server);
    }

    @Override
    public Server ping(String serverIPAddress) throws IOException {
        System.out.println("Pinging server IP: " + serverIPAddress);
        //  Call repo method to get server from database with matching serverIPAddress.
        Server server = serverRepo.findByServerIPAddress(serverIPAddress);
        //  Ping the server.
        InetAddress address = InetAddress.getByName(serverIPAddress);
        //  Ping server with IPAddress, status server_up if reachable within 10000 ms, else server_down.
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        //  Save server in database.
        serverRepo.save(server);
        return server;
    }

    //  Get all servers with a limit parameter.
    @Override
    public Collection<Server> serverList(int limit) {
        System.out.println("Fetching all servers, with page limit of " + limit + ".");
        //  Return servers with a page limit set by arg.
        return serverRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    //  Get server by serverId.
    @Override
    public Server get(Long serverId) {
        System.out.println("Fetch server by serverId: " + serverId);
        return serverRepo.findById(serverId).get();
    }

    //  Updating server in database.
    @Override
    public Server update(Server server) {
        System.out.println("Updating server: " + server.getClass().getName());
        //  Update server in database. Jpa finds the server by the PID (serverId).
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long serverId) {
        System.out.println("Deleting server: " + serverId);
        //  Delete server with matching serverId from database.
        serverRepo.deleteById(serverId);
        //  Return true if deletion is successful.
        return TRUE;
    }

    //  Return image URL for the server. Assign a random image for it.
    private String setServerImageUrl(){
        String[] serverImageNames = { "server1.png", "server2.png", "server3.png", "server4.png" };
        //  Get path to image files, append random number from random (4) to get image.
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + serverImageNames[new Random().nextInt(4)]).toUriString();
    }
}
