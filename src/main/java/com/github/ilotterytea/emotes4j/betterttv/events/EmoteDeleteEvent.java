package com.github.ilotterytea.emotes4j.betterttv.events;

import com.github.ilotterytea.emotes4j.core.Event;

public class EmoteDeleteEvent extends Event {
    private String emoteId;
    private String channel;

    public EmoteDeleteEvent(String emoteId, String channel) {
        this.emoteId = emoteId;
        this.channel = channel;
    }

    public EmoteDeleteEvent() {
    }

    public String getEmoteId() {
        return emoteId;
    }

    public String getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "EmoteDeleteEvent{" +
                "emoteId='" + emoteId + '\'' +
                ", channel='" + channel + '\'' +
                "}";
    }
}
