package com.example.demo.model;

import com.example.demo.enumeration.ClientStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import static jakarta.persistence.GenerationType.AUTO;

//  Annotation to map to database table.
@Entity
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Client {
    //  PID.
    @Id
    @GeneratedValue(strategy=AUTO)
    private Long clientId;
    //  Constraints- fields must be filled in.
    @Column(unique=true)
    @NotEmpty(message="Client first name cannot be empty.")
    private String clientFirstName;
    @Column(unique=true)
    @NotEmpty(message="Client last name cannot be empty.")
    private String clientLastName;
    @Column(unique=true)
    @NotEmpty(message="Client company cannot be empty.")
    private String clientCompany;
    @Column(unique=true)
    @NotEmpty(message="Client company URL cannot be empty.")
    private String clientCompanyURL;
    private String clientEmail;
    private String clientPhoneCountryCode;
    private String clientPhone;
    private ClientStatus clientStatus;

    //  TODO: Annotations retest: if AllArgsConstructor, Constructor may not be needed (for ManagementSystemApplication.java.)
    //  TODO: Servers return status and statusCode in HttpRequest only. DB has only status.
    public Client(Long clientId, String clientFirstName, String clientLastName, String clientCompany, String clientCompanyURL, String clientEmail, String clientPhoneCountryCode, String clientPhone, ClientStatus clientStatus){
        this.clientId = clientId;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientCompany = clientCompany;
        this.clientCompanyURL = clientCompanyURL;
        this.clientEmail = clientEmail;
        this.clientPhoneCountryCode = clientPhoneCountryCode;
        this.clientPhone = clientPhone;
        this.clientStatus = clientStatus;
    }

    //  TODO: Annotations retest - IntelliJ suggested add-in to make ClientServiceImplementation.java work.

    public void setStatus(ClientStatus status) {
    }

    //  TODO: Annotations retest - ClientResource.java needs it to call status method.
    public String getStatus(){
        return this.clientStatus.getStatus();
    }
}
