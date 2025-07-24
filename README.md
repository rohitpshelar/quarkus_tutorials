# quarkus_tutorials https://youtube.com/playlist?list=PLzdlNxYnNoaf5bb-Pwb7MbWHGlRf28pVO&si=fUuG6enQWCokfN4t
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

