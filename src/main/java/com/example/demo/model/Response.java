package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.Map;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

//  Return response to user every time a request is made to the application, for consistency across application.

@Data
// @SuperBuilder
@JsonInclude(NON_NULL)
public class Response {
    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String errorMessage;
    protected String successMessage;
    protected String detailedMessage;
    protected Map<?, ?> data;

    //  TODO: constructor as builder not working???
    //  TODO: clean up message- appears 3 times.
    // /*
    public Response(LocalDateTime timeStamp, int statusCode, HttpStatus status, String errorMessage, String successMessage, String detailedMessage, Map<?, ?> data){
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.status = status;
        this.errorMessage = errorMessage;
        this.successMessage = successMessage;
        this.detailedMessage = detailedMessage;
        this.data = data;
    }
    // */

}
