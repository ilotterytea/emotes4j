package com.github.ilotterytea.emotes4j.betterttv.badges;

import com.github.ilotterytea.emotes4j.betterttv.users.BetterTTVBaseUser;

public class BadgeUser extends BetterTTVBaseUser {
    private Badge badge;

    public BadgeUser(String id, String name, String displayName, String providerId, Badge badge) {
        super(id, name, displayName, providerId);
        this.badge = badge;
    }

    public BadgeUser() {
    }

    public Badge getBadge() {
        return badge;
    }
}
