package com.example.demo.model;

import com.example.demo.enumeration.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import static jakarta.persistence.GenerationType.AUTO;

//  Annotation to map to database table.
@Entity
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Server {
    //  PID.
    @Id
    @GeneratedValue(strategy=AUTO)
    private Long serverId;
    //  Constraint on serverIPAddress: must be filled in.
    @Column(unique=true)
    @NotEmpty(message="IP Address cannot be empty.")
    private String serverIPAddress;
    private String serverName;
    private String serverMemory;
    private String serverType;
    private String serverImageUrl;
    private Status serverStatus;

    //  TODO: Annotations retest: if AllArgsConstructor, Constructor may not be needed (for ManagementSystemApplication.java.)
    //  TODO: Servers return status and statusCode in HttpRequest only. DB has only status.
    public Server(Long serverId, String serverIPAddress, String serverName, String serverMemory, String serverType, String serverImageUrl, Status serverStatus){
        this.serverId = serverId;
        this.serverIPAddress = serverIPAddress;
        this.serverName = serverName;
        this.serverMemory = serverMemory;
        this.serverType = serverType;
        this.serverImageUrl = serverImageUrl;
        this.serverStatus = serverStatus;
    }

    //  TODO: Annotations retest - IntelliJ suggested add-in to make ServerServiceImplementation.java work.
    public void setServerImageUrl(String s) {
    }
    public void setStatus(Status status) {
    }
    
    //  TODO: Annotations retest - ServerResource.java needs it to call status method.
    public String getStatus(){
        return this.serverStatus.getStatus();
    }
}
