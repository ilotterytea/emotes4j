package com.github.ilotterytea.emotes4j.betterttv.users;

import com.github.ilotterytea.emotes4j.betterttv.emotes.Emote;

import java.util.ArrayList;

public class BetterTTVUser {
    private String id;
    private ArrayList<String> bots;
    private String avatarUrl;
    private ArrayList<Emote> channelEmotes;
    private ArrayList<Emote> sharedEmotes;

    public BetterTTVUser(String id, ArrayList<String> bots, String avatarUrl, ArrayList<Emote> channelEmotes, ArrayList<Emote> sharedEmotes) {
        this.id = id;
        this.bots = bots;
        this.avatarUrl = avatarUrl;
        this.channelEmotes = channelEmotes;
        this.sharedEmotes = sharedEmotes;
    }

    public BetterTTVUser() {
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getBots() {
        return bots;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public ArrayList<Emote> getChannelEmotes() {
        return channelEmotes;
    }

    public ArrayList<Emote> getSharedEmotes() {
        return sharedEmotes;
    }

    @Override
    public String toString() {
        return "BetterTTVUser{" +
                "id='" + id + '\'' +
                ", bots=" + bots +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", channelEmotes=" + channelEmotes +
                ", sharedEmotes=" + sharedEmotes +
                '}';
    }
}
