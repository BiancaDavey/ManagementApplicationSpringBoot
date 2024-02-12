package com.example.demo.repo;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
    //  findBy: Find data (row) by specified attribute (column). Note: unique in class.
    Client findClientByClientCompanyURL(String clientCompanyURL);
}
