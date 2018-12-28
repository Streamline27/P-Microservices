##Installation instructions:

1) Checkout repository
2) `File > New > Module from existing source > build.gradle` Do that for each microservice, so that all microservices
are in same IntelliJ project.
3) Build docker image for each microservice 
with `./gradlew dockerBuildImage` command 
or invoke that from IntelliJ `Gradle > [Service name] > Tasks > docker > dockerBuildImage`

## Microservices

There are 4 microservices in the system:

1) **Service registry** - used for load balancing service discovery.
Each service tries to register it self on startup, so **Run it** first
2) **Zuul** - Edge server. Used as a proxy that forwards requests and does load balancing.
Works as a gate to microservice system 
3) **Process** - Responsible for following functions:
    * Show all loan applications
    * Validate application
    * Reject application
    * Confirm application
    * Store applications
4) **Loans** - Responsible for following cases:
    * Compute schedule for confirmation
    * Get schedule for loan
    * Store confirmed loans
    * Store loan shedule
    
# Running everything
Each microservice can function **independently** with use of H2 database and swagger.
(However run service registry, so there wont be huge stacktrace exceptions)

It is also possible to run the system in **cluster** by the means of docker. (This is cool actually)

To run services in cluster create separate terminals and::
1) Go to `Zuul/` and execute `docker-compose up`
2) Go to `Process/` and execute `docker-compose up`
3) Go to `Loans/` and execute `docker-compose up`

# Seeing the results

1) `http://localhost:8762/` - service registry.
2) `http://localhost:8080/swagger-ui.html` - Swagger for both microservices.
    * Use *Select a spec* dropdown to swith between *Loans/Process*
    * Use ping to see load balancing in action
3) Try different endoint flows on Process node swagger.
4) Each microservice can be accessed directly or through Zool
    * e.g. `http://localhost:8080/loans-node/ping` and `http://localhost:8086/loans-node/ping`
    
# Links

#### Zuul docker-compose

| Node                   | URL                                   |
| -----------------------|---------------------------------------|
| service-registry-a     | http://localhost:8762/                |
| service-registry-b     | http://localhost:8763/                |
| zuul-server            | http://localhost:8080/                |
| zuul-server swagger    | http://localhost:8080/swagger-ui.html |

#### Loans docker-compose

| Node                   | URL                                               |
| -----------------------|---------------------------------------------------|
| loans-node-A           | http://localhost:8085/loans-node/ping             |
| loans-node-A swagger   | http://localhost:8085/loans-node/swagger-ui.html  |
| loans-node-B           | http://localhost:8086/loans-node/ping             |
| loans-node-A swagger   | http://localhost:8086/loans-node/swagger-ui.html  |
| loans-db console       | http://http://localhost:81                        |

#### Process docker-compose

| Node                   | URL                                                |
| -----------------------|----------------------------------------------------|
| process-node-A         | http://localhost:8090/process-node/ping            |
| process-node-A swagger | http://localhost:8090/process-node/swagger-ui.html |
| process-node-B         | http://localhost:8091/process-node/ping            |
| process-node-A swagger | http://localhost:8091/process-node/swagger-ui.html |
| process-db console     | http://http://localhost:82 (set Jdbc port to 1522) |

