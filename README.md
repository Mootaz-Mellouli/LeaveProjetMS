# Project: Management Leave

## Description
This project is designed to manage leave requests and related events, teams, and claims. It consists of multiple microservices using different databases and a server for service discovery.

## Microservices:

1. **Leave MS (Leave Management Microservice)**
    - Database: MySQL
    - Description: Handles leave requests and related functionalities.
    
2. **Event MS (Event Management Microservice)**
    - Database: H2
    - Description: Manages events and related functionalities.
    
3. **Team MS (Team Management Microservice)**
    - Database: PostgreSQL
    - Description: Handles team-related operations and functionalities.
    
4. **Claim MS (Claim Management Microservice)**
    - Database: PostgreSQL
    - Framework: Symfony 5
    - Description: Manages claims and related operations.

## Infrastructure Components:

- **Eureka Server**
    - Description: Service discovery server for registering and locating microservices.
    
- **Gateway**
    - Description: Acts as an API gateway to manage incoming requests and route them to appropriate microservices.
    
- **Keycloak**
    - Description: Open-source Identity and Access Management for securing microservices.

## Setup and Installation

1. **Dependencies**
    - Java Development Kit (JDK)
    - MySQL
    - H2
    - PostgreSQL
    - Symfony 5 (for Claim MS)
    - Eureka Server
    - Gateway
    - Keycloak

2. **Configuration**
    - Configure each microservice's database connection settings in their respective configuration files.
    - Configure Eureka Server, Gateway, and Keycloak according to your environment.

3. **Build and Run**
    - Build each microservice using their respective build tools (e.g., Maven, Gradle).
    - Run Eureka Server, Gateway, Keycloak, and each microservice.

4. **Accessing APIs**
    - Once the services are up and running, access the APIs through the Gateway.
    - Refer to the API documentation for each microservice for details on endpoints and payloads.

