package com.github.ilotterytea.emotes4j.betterttv;

import com.github.ilotterytea.emotes4j.betterttv.events.EmoteCreateEvent;
import com.github.ilotterytea.emotes4j.betterttv.events.EmoteDeleteEvent;
import com.github.ilotterytea.emotes4j.betterttv.events.EmoteUpdateEvent;
import com.github.ilotterytea.emotes4j.core.Event;
import com.github.ilotterytea.emotes4j.core.EventClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;


public class BetterTTVEventClient extends EventClient {
    private final Logger log = LoggerFactory.getLogger(BetterTTVEventClient.class);

    public BetterTTVEventClient() {
        super();
        this.client = new WebSocketClient(URI.create("wss://sockets.betterttv.net/ws")) {
            @Override
            public void onOpen(ServerHandshake handshake) {
                log.info("Connected to BetterTTV Event API: {} {}", handshake.getHttpStatus(), handshake.getHttpStatusMessage());
            }

            @Override
            public void onMessage(String message) {
                log.debug("Received a BetterTTV payload: {}", message);

                Gson gson = new Gson();
                JsonObject json = JsonParser.parseString(message).getAsJsonObject();
                String opName = json.get("name").getAsString();

                try {
                    JsonObject data = json.getAsJsonObject("data");
                    Event event = switch (opName) {
                        // Emote create
                        case "emote_create" -> gson.fromJson(data, EmoteCreateEvent.class);
                        // Emote update
                        case "emote_update" -> gson.fromJson(data, EmoteUpdateEvent.class);
                        // Emote delete
                        case "emote_delete" -> gson.fromJson(data, EmoteDeleteEvent.class);
                        // Lookup user
                        //case "lookup_user" -> gson.fromJson(data, LookupUserEvent.class);
                        default -> null;
                    };

                    if (event == null) {
                        return;
                    }

                    eventManager.callEvent(event.getClass(), event);
                } catch (Exception e) {
                    log.error("An exception occurred while processing a BetterTTV event", e);
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                handleCloseEvent(code, reason);
            }

            @Override
            public void onError(Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    @Override
    public boolean subscribeChannel(String userId) {
        if (this.subscriptions.containsKey(userId)) return false;

        client.send(String.format("""
                {
                    "name": "join_channel",
                    "data": {
                        "name": "twitch:%s"
                    }
                }""", userId));

        this.subscriptions.put(userId, userId);
        return true;
    }

    @Override
    public boolean unsubscribeChannel(String userId) {
        if (!this.subscriptions.containsKey(userId)) return false;

        client.send(String.format("""
                {
                    "name": "part_channel",
                    "data": {
                        "name": "twitch:%s"
                    }
                }""", userId));

        this.subscriptions.remove(userId);
        return true;
    }
}
