package com.github.ilotterytea.emotes4j.betterttv.users;

public class BetterTTVBaseUser {
    private String id;
    private String name;
    private String displayName;
    private String providerId;

    public BetterTTVBaseUser(String id, String name, String displayName, String providerId) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.providerId = providerId;
    }

    public BetterTTVBaseUser() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getProviderId() {
        return providerId;
    }
}
