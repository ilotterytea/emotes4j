package com.github.ilotterytea.emotes4j.seventv;

import com.github.ilotterytea.emotes4j.core.Event;
import com.github.ilotterytea.emotes4j.core.EventClient;
import com.github.ilotterytea.emotes4j.seventv.api.SevenTVAPIClient;
import com.github.ilotterytea.emotes4j.seventv.api.users.User;
import com.github.ilotterytea.emotes4j.seventv.events.EmoteSetUpdateEvent;
import com.github.ilotterytea.emotes4j.seventv.events.HeartbeatEvent;
import com.github.ilotterytea.emotes4j.seventv.events.HelloEvent;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Optional;


public class SevenTVEventClient extends EventClient {
    private final Logger log = LoggerFactory.getLogger(SevenTVEventClient.class);

    public SevenTVEventClient() {
        super();
        this.client = new WebSocketClient(URI.create("wss://events.7tv.io/v3")) {
            @Override
            public void onOpen(ServerHandshake handshake) {
                log.info("Connected to 7TV Event API: {} {}", handshake.getHttpStatus(), handshake.getHttpStatusMessage());
            }

            @Override
            public void onMessage(String message) {
                log.debug("Received a 7TV payload: {}", message);

                Gson gson = new Gson();
                JsonObject json = JsonParser.parseString(message).getAsJsonObject();
                int opCode = json.get("op").getAsInt();

                try {
                    JsonObject data = json.getAsJsonObject("d");
                    Event event = switch (opCode) {
                        // Dispatch
                        case 0 -> defineDispatchEvent(gson, data);
                        // Hello
                        case 1 -> gson.fromJson(data, HelloEvent.class);
                        // Heartbeat
                        case 2 -> gson.fromJson(data, HeartbeatEvent.class);
                        // Reconnect
                        //case 4 -> gson.fromJson(data, ReconnectEvent.class);
                        // Ack
                        //case 5 -> gson.fromJson(data, AcknowledgeEvent.class);
                        // Error
                        //case 6 -> gson.fromJson(data, ErrorEvent.class);
                        // End of stream
                        //case 7 -> gson.fromJson(data, EndOfStream.class);
                        default -> null;
                    };

                    if (event == null) {
                        return;
                    }

                    eventManager.callEvent(event.getClass(), event);
                } catch (Exception e) {
                    log.error("An exception occurred while processing a 7TV event", e);
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
        Optional<User> user = SevenTVAPIClient.getUser(userId);

        if (user.isEmpty()) return false;

        client.send(String.format("""
                {
                    "op": 35,
                    "d": {
                        "type": "emote_set.update",
                        "condition": {
                            "object_id": "%s"
                        }
                    }
                }""", user.get().getEmoteSetId()));

        this.subscriptions.put(userId, user.get().getEmoteSetId());
        return true;
    }

    @Override
    public boolean unsubscribeChannel(String userId) {
        if (!this.subscriptions.containsKey(userId)) return false;
        Optional<User> user = SevenTVAPIClient.getUser(userId);

        if (user.isEmpty()) return false;

        client.send(String.format("""
                {
                    "op": 36,
                    "d": {
                        "type": "emote_set.update",
                        "condition": {
                            "object_id": "%s"
                        }
                    }
                }""", user.get().getEmoteSetId()));

        this.subscriptions.remove(userId);
        return true;
    }

    private Event defineDispatchEvent(Gson gson, JsonObject data) {
        String type = data.get("type").getAsString();
        String body = data.getAsJsonObject("body").toString();

        if (type.equals("emote_set.update")) {
            return gson.fromJson(body, EmoteSetUpdateEvent.class);
        }

        return null;
    }
}
