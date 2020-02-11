# LogA
Sick of writing log statesment that decrease the readability of your actual business logic?
```java 
  public ProductPrice productPrice(Long id, LocalDate date) {
    log.debug("productPrice: id: " + id + ", date: " + date);
    // business logic
  }
  ```
  Why not use a handy annotation: ```@LogInput```?
  
  ## Getting Started
  add the following to your ```build.gradle```
```gradle 
plugins {
    // plugin to do the aspect weaving
    id("io.freefair.aspectj.post-compile-weaving") version "4.1.6"
}

// Enable aspectj compile weaving into test classes, this is only needed if you want to use the aspeact weaving on test classes as well
compileTestJava.ajc.options.aspectpath.from sourceSets.main.output

dependencies {
    aspect('libs/LogA-0.1.0.jar')
    // you need a implementation of log4j2-api
    compile(group: 'org.apache.logging.log4j', name: 'log4j-core', version: '2.13.0')
}
```
