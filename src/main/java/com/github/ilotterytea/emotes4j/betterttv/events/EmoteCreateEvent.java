package com.github.ilotterytea.emotes4j.betterttv.events;

import com.github.ilotterytea.emotes4j.betterttv.emotes.Emote;
import com.github.ilotterytea.emotes4j.core.Event;

public class EmoteCreateEvent extends Event {
    private Emote emote;
    private String channel;

    public EmoteCreateEvent(Emote emote, String channel) {
        this.emote = emote;
        this.channel = channel;
    }

    public EmoteCreateEvent() {
    }

    public Emote getEmote() {
        return emote;
    }

    public String getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "EmoteCreateEvent{" +
                "emote=" + emote +
                ", channel='" + channel + '\'' +
                "}";
    }
}
