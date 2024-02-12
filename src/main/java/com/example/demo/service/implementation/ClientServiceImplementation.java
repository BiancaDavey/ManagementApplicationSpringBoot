package com.example.demo.service.implementation;

import com.example.demo.enumeration.ClientStatus;
import com.example.demo.model.Client;
import com.example.demo.repo.ClientRepo;
import com.example.demo.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.util.Collection;
import java.util.Random;

import static java.lang.Boolean.TRUE;
// TODO: new for ping URL
import java.net.URL;
import java.net.HttpURLConnection;

//  Implements the methods in the ClientService interface.
//@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ClientServiceImplementation implements ClientService {
    private final ClientRepo clientRepo;
    public ClientServiceImplementation(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public Client create(Client client) {
        System.out.println("Saving new client: " + client.getClass().getName());
        //  Save client in the database.
        return clientRepo.save(client);
    }

    //  TODO: error handling.
    //  Ping client company URL.
    @Override
    public Client ping(String clientCompanyURL) throws IOException {
        System.out.println("Pinging client company URL: " + clientCompanyURL);
        String connectResult = "";
        //  Call repo method to get client from database with matching company URL.
        Client client = clientRepo.findClientByClientCompanyURL(clientCompanyURL);
        //  Ping the specified URL with HTTP GET request.
        try {
            URL urlObj = new URL(clientCompanyURL);
            HttpURLConnection connect = (HttpURLConnection) urlObj.openConnection();
            connect.setRequestMethod("GET");
            //  Set timeout for connection.
            connect.setConnectTimeout(3000);
            connect.connect();
            int code = connect.getResponseCode();
            if (code == 200){
                connectResult = "Successful";
            }
            if (code != 200){
                connectResult = "Failed";
            }
        } catch (IOException e) {
            connectResult = "Failed";
        }
        //  Set status based on ping success or fail.
        client.setStatus(connectResult.equals("Successful") ? ClientStatus.ACTIVE : ClientStatus.INACTIVE);
        //  Save client in database.
        clientRepo.save(client);
        return client;
    }

    //  Get all clients with a limit parameter.
    @Override
    public Collection<Client> clientList(int limit) {
        System.out.println("Fetching all clients, with page limit of " + limit + ".");
        //  Return clients with a page limit set by arg.
        return clientRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    //  Get client by clientId.
    @Override
    public Client get(Long clientId) {
        System.out.println("Fetch client by clientId: " + clientId);
        return clientRepo.findById(clientId).get();
    }

    //  Updating client in database.
    @Override
    public Client update(Client client) {
        System.out.println("Updating client: " + client.getClass().getName());
        //  Update client in database. Jpa finds the client by the PID (clientId).
        return clientRepo.save(client);
    }

    @Override
    public Boolean delete(Long clientId) {
        System.out.println("Deleting client: " + clientId);
        //  Delete client with matching clientId from database.
        clientRepo.deleteById(clientId);
        //  Return true if deletion is successful.
        return TRUE;
    }
}
