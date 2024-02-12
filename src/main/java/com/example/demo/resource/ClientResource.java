package com.example.demo.resource;

import com.example.demo.model.Client;
import com.example.demo.model.ClientResponse;
import com.example.demo.service.implementation.ClientServiceImplementation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

//  Controller.

@RestController
@RequestMapping("/client")
//@RequiredArgsConstructor
public class ClientResource {
    private final ClientServiceImplementation clientServiceImplementation;

    //  TODO: Annotations retest.
    public ClientResource(ClientServiceImplementation clientServiceImplementation) {
        this.clientServiceImplementation = clientServiceImplementation;
    }

    //  Get clients with limit.
    //  GetMapping - GET request to http://localhost:8080/client/clientList.
    @GetMapping("/clientList")
    public ResponseEntity<ClientResponse> getClients() {
        LocalDateTime timeStamp = LocalDateTime.now();
        Map<?, ?> data = Map.of("clients", clientServiceImplementation.clientList(30));
        String message = "Clients retrieved.";
        HttpStatus status = HttpStatus.OK;
        int statusCode = OK.value();
        return ResponseEntity.ok(new ClientResponse(timeStamp, statusCode, status, message, data));
    }

    //  TODO: error handling.
    //  Ping function for URL of website.
    //  GetMapping - GET request to http://localhost:8080/client/ping/{clientCompanyURL}.
    @GetMapping("/ping/{clientCompanyURL}")
    public ResponseEntity<ClientResponse> pingURL(@PathVariable("clientCompanyURL") String clientCompanyURL) throws IOException {
        Client client = clientServiceImplementation.ping(clientCompanyURL);
        LocalDateTime timeStamp = LocalDateTime.now();
        Map<?, ?> data = Map.of("client", client);
        String message = "Called client ping function.";
        if (client.getStatus().equals("ACTIVE")){
            message = "Ping company site successful.";
        }
        else if (client.getStatus().equals("INACTIVE")){
            message = "Ping company site failed.";
        }
        HttpStatus status = HttpStatus.OK;
        int statusCode = OK.value();
        return ResponseEntity.ok(new ClientResponse(timeStamp, statusCode, status, message, data));
    }

    //  Add a new client.
    //  PostMapping - POST request to http://localhost:8080/client/save.
    @PostMapping("/save")
    public ResponseEntity<ClientResponse> saveClient(@RequestBody @Valid Client client) {
        LocalDateTime timeStamp = LocalDateTime.now();
        Map<?, ?> data = Map.of("client", clientServiceImplementation.create(client));
        String message = "Client added.";
        HttpStatus status = CREATED;
        int statusCode = CREATED.value();
        return ResponseEntity.ok(new ClientResponse(timeStamp, statusCode, status, message, data));
    }

    //  Get a client by clientID.
    //  GetMapping - GET request to http://localhost:8080/client/get/{clientId}.
    @GetMapping("/get/{clientId}")
    public ResponseEntity<ClientResponse> getSite(@PathVariable("clientId") Long clientId) {
        LocalDateTime timeStamp = LocalDateTime.now();
        Map<?, ?> data = Map.of("client", clientServiceImplementation.get(clientId));
        String message = "Client retrieved.";
        HttpStatus status = HttpStatus.OK;
        int statusCode = OK.value();
        return ResponseEntity.ok(new ClientResponse(timeStamp, statusCode, status, message, data));
    }

    //  Delete a client.
    //  DeleteMapping - DELETE request to http://localhost:8080/client/delete/{clientId}.
    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<ClientResponse> deleteClient(@PathVariable("clientId") Long clientId) {
        LocalDateTime timeStamp = LocalDateTime.now();
        Map<?, ?> data = Map.of("deleted", clientServiceImplementation.delete(clientId));
        String message = "Client deleted.";
        HttpStatus status = HttpStatus.OK;
        int statusCode = OK.value();
        return ResponseEntity.ok(new ClientResponse(timeStamp, statusCode, status, message, data));
    }
}