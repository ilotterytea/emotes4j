package com.github.ilotterytea.emotes4j.betterttv.events;

import com.github.ilotterytea.emotes4j.betterttv.emotes.Emote;
import com.github.ilotterytea.emotes4j.betterttv.emotes.EmoteBase;
import com.github.ilotterytea.emotes4j.core.Event;

public class EmoteUpdateEvent extends Event {
    private Emote emote;
    private Emote oldEmote;
    private String channel;

    public EmoteUpdateEvent(Emote emote, Emote oldEmote, String channel) {
        this.emote = emote;
        this.oldEmote = oldEmote;
        this.channel = channel;
    }

    public EmoteUpdateEvent() {

    }

    public EmoteBase getEmote() {
        return emote;
    }

    public Emote getOldEmote() {
        return oldEmote;
    }

    public String getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "EmoteUpdateEvent{" +
                "emote=" + emote +
                ", oldEmote=" + oldEmote +
                ", channel='" + channel + '\'' +
                '}';
    }
}
