package com.github.ilotterytea.emotes4j.betterttv.emotes;

import com.github.ilotterytea.emotes4j.betterttv.users.BetterTTVBaseUser;

public class Emote extends EmoteBase {
    private String imageType;
    private Boolean animated;
    private BetterTTVBaseUser user;

    public Emote(String id, String code, String imageType, Boolean animated, BetterTTVBaseUser user) {
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

    public BetterTTVBaseUser getUser() {
        return user;
    }
}
