package com.github.ilotterytea.emotes4j.betterttv.badges;

import com.google.gson.annotations.SerializedName;

public class Badge {
    public enum BadgeType {
        @SerializedName("1")
        DEVELOPER,
        @SerializedName("2")
        SUPPORT_VOLUNTEER,
        @SerializedName("3")
        EMOTE_APPROVER,
        @SerializedName("4")
        TRANSLATOR
    }

    private String description;
    @SerializedName("svg")
    private String url;
    private BadgeType type;

    public Badge(String description, String url, BadgeType type) {
        this.description = description;
        this.url = url;
        this.type = type;
    }

    public Badge() {
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public BadgeType getType() {
        return type;
    }
}
