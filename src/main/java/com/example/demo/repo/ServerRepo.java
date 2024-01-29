package com.example.demo.repo;

import com.example.demo.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long> {
    //  findBy: Find data (row) by specified attribute (column). Note: unique in class.
    Server findByServerIPAddress(String serverIPAddress);
}
