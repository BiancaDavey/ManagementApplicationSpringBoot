# Management Application - Spring Boot

This project utilises Spring Boot, Java and MySQL and is the backend for the full-stack Management Application, which enables users to manage clients.

This application runs with the Angular [frontend](https://github.com/BiancaDavey/ManagementApplicationAngular).

## Features

* Full stack application using Java Spring Boot, MySQL, and Angular
* Backend application providing model and database for clients

## Requirements

* Angular
* MySQL
* Java
* Spring Boot
* This application runs with the [frontend](https://github.com/BiancaDavey/ManagementApplicationAngular)

## Run the Application locally

1. Navigate into the `server` folder.
2. Run the backend application in IntelliJ.
3. Navigate into the `server-app` folder from the [frontend](https://github.com/BiancaDavey/ManagementApplicationAngular) repository.
4. Start the frontend application: `ng serve`.
5. Navigate to `http://localhost:4200/` to view the frontend application.
6. View the database in MySQL by starting MySQL and running `use serverDB;`, `select * from client;`.

## Application Usage

* The application is initialised with example client data in `src/main/java/com/example/demo/ManagementSystemApplication.java`.
* Add a new client by clicking on the 'New Client' button and filling in the fields
* Delete an existing client by clicking on the trash icon next to the client entry
* Filter clients by status by clicking on the "ALL" button to select the applicable status

## Usage Example

* MySQL Database
[![My-SQLExample-Data.png](https://i.postimg.cc/ZY8d6ycn/My-SQLExample-Data.png)](https://postimg.cc/kD5GmGLP)

## Frontend Usage Example

* From [frontend](https://github.com/BiancaDavey/ManagementApplicationAngular): Client application
  [![Client-Application.png](https://i.postimg.cc/kMzpRzTq/Client-Application.png)](https://i.postimg.cc/kMzpRzTq/Client-Application.png)

* From [frontend](https://github.com/BiancaDavey/ManagementApplicationAngular): Add new client
  [![Client-Application-Add.png](https://i.postimg.cc/Qx7nQmz5/Client-Application-Add.png)](https://i.postimg.cc/Qx7nQmz5/Client-Application-Add.png)

* From [frontend](https://github.com/BiancaDavey/ManagementApplicationAngular): Filter clients
  [![Client-Application-Filter.png](https://i.postimg.cc/J7GFnMcp/Client-Application-Filter.png)](https://i.postimg.cc/J7GFnMcp/Client-Application-Filter.png)