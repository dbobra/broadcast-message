package com.cluster.sender.verticle;

import com.message.helper.HttpServerHelper;
import com.message.helper.RouterHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClusteredSender extends AbstractVerticle {

    /**
     * @param promise
     */
    @Override
    public void start(Promise<Void> promise) {
        final Router router = RouterHelper.createRouter(vertx, "Hello from clustered messenger example!");
        router.post("/sendForAll/:" + "message").handler(this::sendMessageForAllReceivers);
        HttpServerHelper.createAnHttpServer(vertx, router, config(), promise);
    }

    /**
     * @param routingContext
     */
    private void sendMessageForAllReceivers(RoutingContext routingContext) {
        final EventBus eventBus = vertx.eventBus();
        final String message = routingContext.request().getParam("message");

        eventBus.publish("messenger", message);
        log.info("Current Thread Id {} Is Clustered {} ", Thread.currentThread().getId(), vertx.isClustered());
        routingContext.response().end(message);
    }
}
