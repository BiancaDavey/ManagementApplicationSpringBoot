package com.example.demo.resource;

import com.example.demo.enumeration.Status;
import com.example.demo.model.Response;
import com.example.demo.service.implementation.ServerServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import static com.example.demo.enumeration.Status.SERVER_UP;
import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import com.example.demo.model.Server;

//  Controller.

@RestController
@RequestMapping("/server")
//@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImplementation serverServiceImplementation;

    //  TODO: added in, didn't work with annotations.

    public ServerResource(ServerServiceImplementation serverServiceImplementation) {
        this.serverServiceImplementation = serverServiceImplementation;
    }

    //  Get servers with limit.
    //  GetMapping - GET request to http://localhost:8080/server/serverList.
    @GetMapping("/serverList")
    public ResponseEntity<Response> getServers() {

        //  TODO: trying new object.
        LocalDateTime timeStamp = LocalDateTime.now();
        Map<?, ?> data = Map.of("servers", serverServiceImplementation.serverList(30));
        String message = "Servers retrieved.";
        HttpStatus status = HttpStatus.OK;
        int statusCode = OK.value();
        return ResponseEntity.ok(new Response(timeStamp, statusCode, status, message, message, message, data));

        //  TODO: error with SuperBuilder in Response.java. Original.
        /*
        return ResponseEntity.ok(
                Response.builder
                        .timeStamp(now())
                        .data(Map.of("servers", serverServiceImplementation.serverList(30)))
                        .message("Servers retrieved.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
        */

    }

    //  Ping function.
    //  GetMapping - GET request to http://localhost:8080/ping/{serverIPAddress}.
    @GetMapping("/ping/{serverIPAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("serverIPAddress") String serverIPAddress) throws IOException {
        Server server = serverServiceImplementation.ping(serverIPAddress);

        //  TODO: trying new object.
        LocalDateTime timeStamp = LocalDateTime.now();
        Map<?, ?> data = Map.of("server", server);
        String message = "Called ping function.";
        if (server.getStatus().equals("SERVER_UP")){
            message = "Ping successful.";
        }
        else if (server.getStatus().equals("SERVER_DOWN")){
            message = "Ping failed.";
        }
        // String message = (server.getStatus() == "SERVER_UP" ? "Ping successful." : "Ping failed.");
        HttpStatus status = HttpStatus.OK;
        int statusCode = OK.value();
        return ResponseEntity.ok(new Response(timeStamp, statusCode, status, message, message, message, data));

        //  TODO original.
        /*
        return ResponseEntity.ok(
                Response.builder
                        .timeStamp(now())
                        .data(Map.of("server", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping successful." : "Ping failed.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
        */
    }

    //  Create a new server.
    //  PostMapping - POST request to http://localhost:8080/server/save.
    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {

        //  TODO: trying new object.
        LocalDateTime timeStamp = LocalDateTime.now();
        Map<?, ?> data = Map.of("server", serverServiceImplementation.create(server));
        String message = "Server created.";
        HttpStatus status = CREATED;
        int statusCode = CREATED.value();
        return ResponseEntity.ok(new Response(timeStamp, statusCode, status, message, message, message, data));

        //  TODO: original.
        /*
        return ResponseEntity.ok(
                Response.builder
                        .timeStamp(now())
                        .data(Map.of("server", serverServiceImplementation.create(server)))
                        .message("Server created.")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
        */
    }

    //  Get a server by server ID.
    //  GetMapping - GET request to http://localhost:8080/server/get/{serverID}.
    @GetMapping("/get/{serverID}")
    public ResponseEntity<Response> getServer(@PathVariable("serverID") Long serverID) {

        //  TODO: trying new object.
        LocalDateTime timeStamp = LocalDateTime.now();
        Map<?, ?> data = Map.of("server", serverServiceImplementation.get(serverID));
        String message = "Server retrieved.";
        HttpStatus status = HttpStatus.OK;
        int statusCode = OK.value();
        return ResponseEntity.ok(new Response(timeStamp, statusCode, status, message, message, message, data));

        //  TODO original
        /*
        return ResponseEntity.ok(
                Response.builder
                        .timeStamp(now())
                        .data(Map.of("server", serverServiceImplementation.get(serverID)))
                        .message("Server retrieved.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
        */
    }

    //  Delete a server.
    //  DeleteMapping - DELETE request to http://localhost:8080/server/delete/{serverID}.
    @DeleteMapping("/delete/{serverID}")
    public ResponseEntity<Response> deleteServer(@PathVariable("serverID") Long serverID) {

        //  TODO: trying new object.
        LocalDateTime timeStamp = LocalDateTime.now();
        Map<?, ?> data = Map.of("deleted", serverServiceImplementation.delete(serverID));
        String message = "Server deleted.";
        HttpStatus status = HttpStatus.OK;
        int statusCode = OK.value();
        return ResponseEntity.ok(new Response(timeStamp, statusCode, status, message, message, message, data));


        //  TODO: original
        /*
        return ResponseEntity.ok(
                Response.builder
                        .timeStamp(now())
                        .data(Map.of("deleted", serverServiceImplementation.delete(serverID)))
                        .message("Server deleted.")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
         */
    }

    //  Get image for the server.
    //  GetMapping - GET request to http://localhost:8080/server/image/{fileName}.
    //  TODO: currently finds image in local downloads folder. Doesn't work. Maybe Windows vs Linux.
    //  TODO: issue- use Postman, POST request, leave out- should assign automatically but it's null.
    @GetMapping(path="/server/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        System.out.println("Get server image");
        return Files.readAllBytes(Paths.get(System.getProperty(("user.home") + "/Downloads/images/" + fileName)));
    }
}
