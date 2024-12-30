package com.github.ilotterytea.emotes4j.betterttv;

import com.github.ilotterytea.emotes4j.betterttv.emotes.Emote;
import com.github.ilotterytea.emotes4j.betterttv.events.EmoteCreateEvent;
import com.github.ilotterytea.emotes4j.betterttv.events.EmoteDeleteEvent;
import com.github.ilotterytea.emotes4j.betterttv.events.EmoteUpdateEvent;
import com.github.ilotterytea.emotes4j.betterttv.users.BetterTTVUser;
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
import java.util.ArrayList;
import java.util.Optional;


public class BetterTTVEventClient extends EventClient<ArrayList<Emote>> {
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
                    Event event;

                    switch (opName) {
                        // Emote create
                        case "emote_create": {
                            EmoteCreateEvent e = gson.fromJson(data, EmoteCreateEvent.class);
                            ArrayList<Emote> emotes = subscriptions.getOrDefault(e.getChannel(), new ArrayList<>());
                            emotes.add(e.getEmote());
                            subscriptions.put(e.getChannel(), emotes);
                            event = e;
                            break;
                        }
                        // Emote update
                        case "emote_update": {
                            String emoteId = data.get("emote").getAsJsonObject().get("id").getAsString();
                            String emoteName = data.get("emote").getAsJsonObject().get("code").getAsString();
                            String channel = data.get("channel").getAsString();
                            ArrayList<Emote> emotes = subscriptions.getOrDefault(channel, new ArrayList<>());

                            Optional<Emote> emote = emotes.stream().filter((x) -> x.getId().equals(emoteId)).findFirst();
                            event = emote.map(value -> {
                                Emote newValue = new Emote(
                                        value.getId(),
                                        emoteName,
                                        value.getImageType(),
                                        value.getAnimated(),
                                        value.getUser()
                                );
                                emotes.remove(value);
                                emotes.add(newValue);
                                return new EmoteUpdateEvent(newValue, value, channel);
                            }).orElse(null);
                            break;
                        }
                        // Emote delete
                        case "emote_delete": {
                            String emoteId = data.get("emoteId").getAsString();
                            String channel = data.get("channel").getAsString();
                            ArrayList<Emote> emotes = subscriptions.getOrDefault(channel, new ArrayList<>());

                            Optional<Emote> emote = emotes.stream().filter((x) -> x.getId().equals(emoteId)).findFirst();
                            event = emote.map(value -> {
                                        emotes.remove(value);
                                        return new EmoteDeleteEvent(value, channel);
                                    }
                            ).orElse(null);
                            break;
                        }
                        // Lookup user
                        //case "lookup_user" -> gson.fromJson(data, LookupUserEvent.class);
                        default: {
                            event = null;
                            break;
                        }
                    }

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
        if (this.subscriptions.containsKey("twitch:" + userId)) return false;

        // Syncing channel emotes
        Optional<BetterTTVUser> user = BetterTTVAPIClient.getTwitchUser(userId);
        if (user.isEmpty()) return false;

        userId = "twitch:" + userId;
        log.info("Subscribing to {}...", userId);

        ArrayList<Emote> emotes = new ArrayList<>();
        emotes.addAll(user.get().getChannelEmotes());
        emotes.addAll(user.get().getSharedEmotes());

        client.send(String.format("""
                {
                    "name": "join_channel",
                    "data": {
                        "name": "%s"
                    }
                }""", userId));

        this.subscriptions.put(userId, emotes);
        return true;
    }

    @Override
    public boolean unsubscribeChannel(String userId) {
        userId = "twitch:" + userId;
        if (!this.subscriptions.containsKey(userId)) return false;
        log.info("Unsubscribing from {}...", userId);

        client.send(String.format("""
                {
                    "name": "part_channel",
                    "data": {
                        "name": "%s"
                    }
                }""", userId));

        this.subscriptions.remove(userId);
        return true;
    }
}
