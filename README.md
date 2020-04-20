[![](https://jitpack.io/v/reebow/LogA.svg)](https://jitpack.io/#reebow/LogA)
[![codecov](https://codecov.io/gh/reebow/LogA/branch/master/graph/badge.svg)](https://codecov.io/gh/reebow/LogA)

# LogA
Sick of writing log statesment that decrease the readability of your actual business logic?
```java 
  public ProductPrice productPrice(Long id, LocalDate date) {
    if (log.isEnabled(DEBUG) {
        log.debug("productPrice: id: " + id + ", date: " + date);
    }
    // business logic
  }
  ```
  Why not use a handy annotation: ```@LogInput```?
  
  ## Getting Started
  Add the following to your ```build.gradle```
```gradle 
plugins {
    // a plugin to do the aspect weaving
    id("io.freefair.aspectj.post-compile-weaving") version "4.1.6"
}
repositories {
    // repository where the library is published
    maven { url "https://jitpack.io" }
}

dependencies {
    // this library
    
    aspect('com.github.reebow:LogA:-SNAPSHOT')
    // you need an implementation of log4j2-api, for example log4j2-core
    compile(group: 'org.apache.logging.log4j', name: 'log4j-core', version: '2.13.0')
}
```
### Usage
```java
@LogInput
private void productPrice(Long id, LocalDate date){ 
  // business logic
}
```
Output could be: 
```
15:43:47.726 [main] DEBUG de.reebow.loga.ProductService - Input arguments for method "productPrice": Parameter type: class java.lang.Long, name: id, value: 1. Parameter type: LocalDate, name: date, value: 2020-02-15. 
```
