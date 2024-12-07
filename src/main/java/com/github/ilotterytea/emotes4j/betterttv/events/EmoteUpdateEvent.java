package com.github.ilotterytea.emotes4j.betterttv.events;

import com.github.ilotterytea.emotes4j.betterttv.emotes.EmoteBase;
import com.github.ilotterytea.emotes4j.core.Event;

public class EmoteUpdateEvent extends Event {
    private EmoteBase emote;
    private String channel;

    public EmoteUpdateEvent(EmoteBase emote, String channel) {
        this.emote = emote;
        this.channel = channel;
    }

    public EmoteUpdateEvent() {

    }

    public EmoteBase getEmote() {
        return emote;
    }

    public String getChannel() {
        return channel;
    }
}
