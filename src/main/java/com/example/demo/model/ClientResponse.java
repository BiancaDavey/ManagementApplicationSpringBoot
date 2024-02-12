package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.Map;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

//  Return response to user every time a request is made to the application, for consistency across application.
//  TODO: check if redundant; if can use Response.

@Data
// @SuperBuilder
@JsonInclude(NON_NULL)
public class ClientResponse {
    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String message;
    protected Map<?, ?> data;

    //  TODO: Annotations retest: if SuperBuilder, Constructor may not be needed.
    public ClientResponse(LocalDateTime timeStamp, int statusCode, HttpStatus status, String message, Map<?, ?> data){
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.data = data;
    }
}