package com.example.demo.enumeration;

public enum ClientStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");
    private final String status;
    //  Constructor.
    ClientStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
}