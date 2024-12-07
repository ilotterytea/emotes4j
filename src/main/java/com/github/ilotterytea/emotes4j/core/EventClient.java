package com.github.ilotterytea.emotes4j.core;

import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.TimerTask;

public abstract class EventClient {
    private final Logger log = LoggerFactory.getLogger(EventClient.class);

    protected final EventManager eventManager;
    protected final HashMap<String, String> subscriptions;
    protected WebSocketClient client;

    private int retryCounter;

    public EventClient() {
        this.eventManager = new EventManager();
        this.subscriptions = new HashMap<>();
        this.retryCounter = 0;
    }

    public abstract boolean subscribeChannel(String userId);

    public abstract boolean unsubscribeChannel(String userId);

    public EventManager getEventManager() {
        return eventManager;
    }

    public HashMap<String, String> getSubscriptions() {
        return subscriptions;
    }

    protected void handleCloseEvent(int code, String message) {
        log.info("Closing connection... Reason: {} {}", code, message);

        if (retryCounter != 0) {
            log.info("The reconnection task wasn't run because the retry counter wasn't reset to zero.");
            return;
        }

        new TimerTask() {
            @Override
            public void run() {
                retryCounter++;

                if (retryCounter > 4) {
                    log.info("Retry limit has been exceeded! Cancelling the reconnection task...");
                    retryCounter = 0;
                    super.cancel();
                    return;
                }

                log.info("Reconnecting...");

                try {
                    if (client.reconnectBlocking()) {
                        log.info("Successfully reconnected!");
                        retryCounter = 0;
                        subscriptions.clear();
                        super.cancel();
                    } else {
                        log.info("Failed to reconnect! Retrying...");
                    }
                } catch (Exception e) {
                    log.error("An exception occurred while reconnecting", e);
                }
            }
        };
    }

    public WebSocketClient getClient() {
        return client;
    }
}
