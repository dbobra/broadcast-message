# Clustered Receiver

This example shows the use of `broadcasting messaging` in the `clustered environment`. The receiver verticle receives messages from the publisher that it's subscribed to.

```java
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        final EventBus eventBus = vertx.eventBus();
        eventBus.consumer("messenger", receivedMessage -> {
            log.debug("Received message: {} ", receivedMessage.body());
        });

        log.info("Receiver ready!");
    }
    
```

## Requirements
* JDK 8 or later
* Maven 3.0.0 or later

## To compile
```bash
mvn clean install
```

## To run
```bash
java -jar target/Receiver-1.0-SNAPSHOT.jar
```
