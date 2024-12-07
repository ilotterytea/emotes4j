package com.github.ilotterytea.emotes4j.betterttv.emotes;

import com.github.ilotterytea.emotes4j.betterttv.users.BetterTTVUser;

public class Emote extends EmoteBase {
    private String imageType;
    private Boolean animated;
    private BetterTTVUser user;

    public Emote(String id, String code, String imageType, Boolean animated, BetterTTVUser user) {
        super(id, code);
        this.imageType = imageType;
        this.animated = animated;
        this.user = user;
    }

    public Emote() {
    }

    public String getImageType() {
        return imageType;
    }

    public Boolean getAnimated() {
        return animated;
    }

    public BetterTTVUser getUser() {
        return user;
    }
}
