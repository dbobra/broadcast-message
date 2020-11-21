# Clustered Sender

This example shows the use of `broadcasting messaging` in the `clustered environment`. The messages you send with the `HTTP Post` requests over `the http://localhost:8080/sendForAll/:message` are forwarded to the all registered receiver.

```java
/**
     *
     * @param routingContext
     */
    private void sendMessage(RoutingContext routingContext){
        final EventBus eventBus = vertx.eventBus();
        final String message = routingContext.request().getParam(PATH_PARAM);

        eventBus.publish("messenger", message);
        log.info("Current Thread Id {} Is Clustered {} ", Thread.currentThread().getId(), vertx.isClustered());
        routingContext.response().end(message);
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
java -jar target/Sender-1.0-SNAPSHOT.jar
#For multiple instances java -jar target/Sender-1.0-SNAPSHOT.jar -instances 2

```