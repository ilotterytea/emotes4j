package com.github.ilotterytea.emotes4j.seventv.events;

import com.github.ilotterytea.emotes4j.core.Event;
import com.github.ilotterytea.emotes4j.seventv.emotes.EventEmote;
import com.github.ilotterytea.emotes4j.seventv.users.Actor;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class EmoteSetUpdateEvent extends Event {
    @SerializedName("id")
    private String emoteSetId;
    private Actor actor;
    @Nullable
    private ArrayList<EventEmote> pulled;
    @Nullable
    private ArrayList<EventEmote> pushed;
    @Nullable
    private ArrayList<EventEmote> updated;

    public EmoteSetUpdateEvent(String emoteSetId, Actor actor, ArrayList<EventEmote> pulled, ArrayList<EventEmote> pushed, ArrayList<EventEmote> updated) {
        this.emoteSetId = emoteSetId;
        this.actor = actor;
        this.pulled = pulled;
        this.pushed = pushed;
        this.updated = updated;
    }

    public EmoteSetUpdateEvent() {
    }

    public String getEmoteSetId() {
        return emoteSetId;
    }

    public Actor getActor() {
        return actor;
    }

    @Nullable
    public ArrayList<EventEmote> getPulled() {
        return pulled;
    }

    @Nullable
    public ArrayList<EventEmote> getPushed() {
        return pushed;
    }

    @Nullable
    public ArrayList<EventEmote> getUpdated() {
        return updated;
    }

    @Override
    public String toString() {
        return "EmoteSetUpdateEvent{" +
                "emoteSetId='" + emoteSetId + '\'' +
                ", actor=" + actor +
                ", pulled=" + pulled +
                ", pushed=" + pushed +
                ", updated=" + updated +
                '}';
    }
}
