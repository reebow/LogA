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
