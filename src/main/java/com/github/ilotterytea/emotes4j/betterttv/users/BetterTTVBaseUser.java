package com.github.ilotterytea.emotes4j.betterttv.users;

public class BetterTTVBaseUser {
    protected String id;
    protected String name;
    protected String displayName;
    protected String providerId;

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

    @Override
    public String toString() {
        return "BetterTTVBaseUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", providerId='" + providerId + '\'' +
                '}';
    }
}
