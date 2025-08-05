# quarkus_tutorials https://youtube.com/playlist?list=PLzdlNxYnNoaf5bb-Pwb7MbWHGlRf28pVO&si=fUuG6enQWCokfN4t

## Spring Boot vs Quarkus Annotations Comparison

| Annotation Type       | Spring Boot            | Quarkus               |
|-----------------------|------------------------|-----------------------|
| **Main Class**        | @SpringBootApplication | @QuarkusMain          |
| **Dependency Inj.**   | @Autowired             | @Inject               |
| **Components**        | @Repository            | @ApplicationScoped    |
|                       | @Service               | @ApplicationScoped    |
|                       | @Component             | @ApplicationScoped    |
| **Data Access**       | CrudRepository         | PanacheRepository     |
|                       | @Entity                | @Entity               |
|                       | @Transactional         | @Transactional        |
| **Web**               | @RestController        | @Path                 |
|                       | @GetMapping            | @GET                  |
|                       | @PostMapping           | @POST                 |
| **Testing**           | @SpringBootTest        | @QuarkusTest          |
|                       | @MockBean              | @InjectMock           |
| **Configuration**     | @Value                 | @ConfigProperty       |
| **Caching**           | @EnableCaching         | @CacheResult          |
| **Scheduling**        | @Scheduled             | @Scheduled            |
| **Async**             | @Async                 | @Blocking/@NonBlocking|



1. Support both

```
Imperative -> if ABC data fetched, it will search entire DB and bring record
Reactive -> If ABC is fetched, it will return data 1 by 1 as it gets.
```

2. Open source
3.  Set JAVA_HOME
4.  Download Maven https://maven.apache.org/download.cgi > Binary zip archive >  keep in C:\Program Files (x86) >  set path (C:\Program Files (x86)\apache-maven-3.9.11\bin) in "PATH" 
5.  Goto https://code.quarkus.io/ 
6. Dependency Selected 
   1. RESTEasy Classic 
   2. RESTEasy Classic Jackson
7. Add project to intellij
8. Add plugin in Intellij as > Quarkus Tools
9. run Project with command ```mvn quarkus:dev``` OR  ```./mvnw quarkus:dev``` (Ref : [Quarkus_README.md](Quarkus_README.md) )
10. In 4th Lecture 
11. To check Quarkus supported Extension/Dependencies -> ```mvn quarkus:list-extensions``` OR ```./mvnw quarkus:list-extensions```
12. To Add  > ```./mvnw quarkus:add-extension -Dextensions="_____"``` OR ```mvn quarkus:add-extension -Dextensions="Mutiny"```
13. In 6th Part - All 4 API added [MobileResource.java](src/main/java/com/learn/resource/MobileResource.java) and their Postman collection [quarkus-tutorial-mvn.postman_collection.json](quarkus-tutorial-mvn.postman_collection.json)
14. In 7th Part - All 4 API updated with modal and 5th API ByNumber ([Mobile.java](src/main/java/com/learn/resource/Mobile.java)) and new resource [MobileResourcePart7.java](src/main/java/com/learn/resource/MobileResourcePart7.java) and their Postman collection [quarkus-tutorial-mvn.postman_collection.json](quarkus-tutorial-mvn.postman_collection.json)
15. In 8th Part -
    1. Add swagger ```mvn quarkus:add-extension -Dextensions="quarkus-smallrye-openapi"```
    2. Goto http://localhost:8080/q/swagger-ui/
    3. To Change default swagger path (http://localhost:8080/q/swagger-ui/) to custom (http://localhost:8080/swag/)
       ```properties
          quarkus.swagger-ui.path=/swag
       ```
    4. To disable swagger UI Path
       ```properties
          quarkus.swagger-ui.always-include=false
       ```
16. In 9th Part -  Bring data from other Micro service
    1. Add following extension/dependency :
       1. "REST Client" to call remote API ```mvn quarkus:add-extension -Dextensions="quarkus-rest-client"```
       2. "REST Client jackson" to call remote API ```mvn quarkus:add-extension -Dextensions="quarkus-rest-client-jackson"```
       3. "quarkus-rest-jackson" to call remote API ```mvn quarkus:add-extension -Dextensions="quarkus-rest-jackson"```
       4. Remove quarkus-resteasy and quarkus-resteasy-jackson due to conflict regards ( Caused by: jakarta.enterprise.inject.spi.DeploymentException: Mixing Quarkus REST and RESTEasy Classic server parts is not supported )
    2. Use DUMMY client as https://www.tvmaze.com/api
       1. Search TV show by Id - https://api.tvmaze.com/shows/169 
       2. Create Modal class for above response
17. In Part-10 : cors ( Cross Origin Resource Sharing ) - allow external service to access our service ( Open  this from Browser : [Part-10.html](Part-10.html) )
    ```properties
    quarkus.http.cors=true
    quarkus.http.cors.origins=*
    ```
    1. Allow only specific method 
    ```properties
    quarkus.http.cors.methodshs=GET, POST
    ```
18. In Part-11, 12 : Microprofile Fault Tolerance
    1. Add following extension/dependency :
       1. "Fault Tolerance" to call default method if remote API offline ```mvn quarkus:add-extension -Dextensions="quarkus-smallrye-fault-tolerance"```
      
    2. Create Fallback Method 
    ```java
    @Fallback(fallbackMethod = "getTvSeriesByIdFallback")
    ```
    3. Retry ```@Retry(maxRetries = 2)```
    4. Timeout ```@Timeout(1000)```
    5. Circuit Breaker
    ```java
    @CircuitBreaker(
        requestVolumeThreshold=2,
        failureRatio=0.5,
        delay = 10, delayUnit = ChronoUnit.SECONDS
    )
    ```
    6. Git Bash Script to hit URL in loop
    ```shell
        while true; do sleep 1; curl http://localhost:8080/tvseries/260; echo -e '\n'; done
    ```
    13. Part-13, 14 : Hibernate-ORM PanacheEntity with H2DB
        1. "H2DB" - ```./mvnw quarkus:add-extension -Dextensions="io.quarkus:quarkus-jdbc-h2"```
        2. "Hibernate ORM" - ```./mvnw quarkus:add-extension -Dextensions="io.quarkus:quarkus-hibernate-orm-panache"```
        3. Two Types
           1. Extend Entity Class with PanacheEntity
           2. Implement with PanacheRepository
        4. Mapping
           1. One to One
              - Add ```@OneToOne``` to secondary class

                <table>
                   <tr>
                      <td>
                         <table>
                            <tr>
                               <th colspan="2">Table 1</th>
                            </tr>
                            <tr>
                               <td>T1_ID</td>
                               <td>T1_Name</td>
                            </tr>
                         </table>
                      </td>
                      <td>
                         <table>
                            <tr>
                               <th colspan="3">Table 2</th>
                            </tr>
                            <tr>
                               <td>T2_ID</td>
                               <td>T2S_Name</td>
                               <td>T1_ID</td>
                            </tr>
                         </table>
                      </td>
                   </tr>
                </table>
    
               - But here we can only find Table 1 from Table 2, So
               - We will add ```@OneToOne``` to Primary class

                <table>
                   <tr>
                      <td>
                         <table>
                            <tr>
                               <th colspan="3">Table 1</th>
                            </tr>
                            <tr>
                               <td>T1_ID</td>
                               <td>T1_Name</td>
                               <td>T2_ID</td>
                            </tr>
                         </table>
                      </td>
                      <td>
                         <table>
                            <tr>
                               <th colspan="3">Table 2</th>
                            </tr>
                            <tr>
                               <td>T2_ID</td>
                               <td>T2S_Name</td>
                               <td>T1_ID</td>
                            </tr>
                         </table>
                      </td>
                   </tr>
                </table>
               - But now we have two column on both table
               - So to remove extra column from Primary class use in Primary class :  ```@OneToOne(mappedBy = "<*Primary class*>")```
               - To fetch both at once use Primary class : **fetch = FetchType.EAGER** : ```@OneToOne(mappedBy = "<*Primary class*>", fetch = FetchType.EAGER)```
               - To fix ```Infinite recursion``` - which is due to both class has ```@OneToOne``` annotation
                 1. Add @JsonManagedReference in Primary class
                 2. Add @JsonBackReference in secondary class
               - To save both at Once use in Primary class  : **cascade = CascadeType.ALL** : ```@OneToOne(mappedBy = "laptop", fetch = FetchType.EAGER, cascade = CascadeType.ALL)```
19. Part 29 : Logging
    1. Info - Method info
    2. Debug - Data Info
    3. warning
    4. Error
20. Part- 30 : Exception -TODO
21. Part - 32 : MicroProfile Health
    1. Add ``` mvn quarkus:add-extension -Dextensions="quarkus-smallrye-health"```
    2. Two Type
       1. Liveness [LivenessHealthCheck.java](src/main/java/com/learn/resource/part30_MicroProfile_health/LivenessHealthCheck.java) - To check Proxy/Client Connections
       2. Readiness [ReadinessHealthCheck.java](src/main/java/com/learn/resource/part30_MicroProfile_health/ReadinessHealthCheck.java) - To check DB connection
    3. Test URLs
       1. http://localhost:8080/q/health-ui/
       2. http://localhost:8080/q/health
       3. http://localhost:8080/q/health/live
       4. http://localhost:8080/q/health/ready
       5. http://localhost:8080/q/health/started
       6. http://localhost:8080/q/health/well
22. Part-33 How to use MicroProfile Metrics 
    1. Add ```mvn quarkus:add-extension -Dextensions="quarkus-smallrye-metrics"```
    2. Types [Number.java](src/main/java/com/learn/resource/part33_microProfile_matrics/Number.java)
       1. @Counted
       ```json
       {
          "com.learn.resource.part33_microProfile_matrics.Number.Count_checkIfPrimeNumber": 3
       }
       ```
   
       2. @Timed
       ```json   
       {
          "com.learn.resource.part33_microProfile_matrics.Number.Time_checkIfPrimeNumber": {
          "p99": 319201.0,
          "min": 15200.0,
          "max": 319201.0,
          "mean": 156003.43605748552,
          "p50": 15600.0,
          "p999": 319201.0,
          "stddev": 142029.00004744556,
          "p95": 319201.0,
          "p98": 319201.0,
          "p75": 280601.0,
          "fiveMinRate": 0.2032510706679223,
          "fifteenMinRate": 0.2011018917421949,
          "meanRate": 0.23204665475695216,
          "count": 4,
          "oneMinRate": 0.21471253794774184,
          "elapsedTime": 630602.0
          }
       }
       ```
       3. @Metered
       ```json5
       {
          "com.learn.resource.part33_microProfile_matrics.Number.Metered_checkIfPrimeNumber": {
          "fiveMinRate": 0.38704854970388447,
          "fifteenMinRate": 0.39559850366986254,
          "meanRate": 0.1737586528638574,
          "count": 4,
          "oneMinRate": 0.34222396825043916
          }
       }
       ```
       4. @Gauge(unit = MetricUnits.MILLISECONDS) - For Custom 
       ```json
       {
          "com.learn.resource.part33_microProfile_matrics.Number.getHighestInputPrimeNumber": 2
       }
       ```
           
    3. Test URLs - http://localhost:8080/q/metrics/application

24. Part-34 Authentication and Authorization using JWT Token and Roles-Based Access Control
    1. Create two Module
       1. App (Course) - All APIs - [course](course)
          1. Run 
                ```shell
                    cd .\course
                    mvn quarkus:add-extension -Dextensions="quarkus-smallrye-jwt" 
                ```
          2. Add in [application.properties](course/src/main/resources/application.properties)
             ```properties
             mp.jwt.verify.issuer=jwt-token
             mp.jwt.verify.publickey.location=../jwt/publicKey.pem
             ```
       2. JWT - Generate Token - [JWT](JWT)
           1. Run
                ```shell
                    cd .\JWT
                    mvn quarkus:add-extension -Dextensions="quarkus-smallrye-jwt" 
                    mvn quarkus:add-extension -Dextensions="quarkus-smallrye-jwt-build" 
                ```
           2. Add in [application.properties](jwt-token/src/main/resources/application.properties)
              ```properties
              smallrye.jwt.sign.key.location=../jwt/privateKey.pem
              ```
       3. Generate Key
          1. Open GitBash
          2. Run
          ```GitBash
             mkdir jwt

             openssl genrsa -out jwt/rsaPrivateKey.pem 2048
    
             openssl rsa -pubout -in jwt/rsaPrivateKey.pem -out jwt/publicKey.pem
    
             openssl pkcs8 -topk8 -nocrypt -inform pem -in jwt/rsaPrivateKey.pem -outform pem -out jwt/privateKey.pem
    
             chmod 600 jwt/rsaPrivateKey.pem
    
             chmod 600 jwt/privateKey.pem
          ```
          3. Folder will be created as jwt ( [jwt](jwt) ), and it will contain following files
             1. [privateKey.pem](jwt/privateKey.pem)
             2. [publicKey.pem](jwt/publicKey.pem)
             3. [rsaPrivateKey.pem](jwt/rsaPrivateKey.pem)
       4. Fix `ignored POM.XML` file issue : Goto Settings > Build, Execution > Build Tools > Maven > IgnoreFiles > Untick Both Module
       5. Validate barrier key 