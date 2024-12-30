package com.github.ilotterytea.emotes4j.seventv.events;

import com.github.ilotterytea.emotes4j.core.Event;
import com.github.ilotterytea.emotes4j.seventv.events.models.Actor;
import com.github.ilotterytea.emotes4j.seventv.events.models.Emote;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class EmoteSetUpdateEvent extends Event {
    @SerializedName("id")
    private String emoteSetId;
    private Actor actor;
    @Nullable
    private ArrayList<Emote> pulled;
    @Nullable
    private ArrayList<Emote> pushed;
    @Nullable
    private ArrayList<Emote> updated;

    public EmoteSetUpdateEvent(String emoteSetId, Actor actor, ArrayList<Emote> pulled, ArrayList<Emote> pushed, ArrayList<Emote> updated) {
        this.emoteSetId = emoteSetId;
        this.actor = actor;
        this.pulled = pulled;
        this.pushed = pushed;
        this.updated = updated;
    }

    public String getEmoteSetId() {
        return emoteSetId;
    }

    public Actor getActor() {
        return actor;
    }

    @Nullable
    public ArrayList<Emote> getPulled() {
        return pulled;
    }

    @Nullable
    public ArrayList<Emote> getPushed() {
        return pushed;
    }

    @Nullable
    public ArrayList<Emote> getUpdated() {
        return updated;
    }
}
