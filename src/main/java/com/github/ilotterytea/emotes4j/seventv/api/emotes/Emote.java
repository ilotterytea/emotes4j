package com.github.ilotterytea.emotes4j.seventv.api.emotes;

import com.google.gson.annotations.SerializedName;

/**
 * @author ilotterytea
 * @since 1.0
 */
public class Emote {
    private String id;
    private String name;
    @SerializedName("actor_id")
    private String actorId;

    public Emote() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    @Override
    public String toString() {
        return "Emote{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", actorId='" + actorId + '\'' +
                '}';
    }
}
