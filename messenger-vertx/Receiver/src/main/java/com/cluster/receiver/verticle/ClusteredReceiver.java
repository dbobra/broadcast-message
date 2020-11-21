package com.cluster.receiver.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClusteredReceiver extends AbstractVerticle {

    @Override
    public void start() {
        final EventBus eventBus = vertx.eventBus();
        eventBus.consumer("messenger", receivedMessage -> log.debug("Received message: {} ", receivedMessage.body()));
        log.info("Receiver ready!");
    }
}
