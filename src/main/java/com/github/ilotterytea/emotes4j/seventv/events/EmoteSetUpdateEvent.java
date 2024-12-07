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
}
