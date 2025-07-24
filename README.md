# quarkus_tutorials
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
9. run command ./mvnw quarkus:dev (Ref : [Quarkus_README.md](Quarkus_README.md) )