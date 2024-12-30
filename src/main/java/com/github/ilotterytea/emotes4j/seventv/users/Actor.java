package com.github.ilotterytea.emotes4j.seventv.users;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Actor {
    public static class Connection {
        private String id;
        private String platform;
        private String username;
        @SerializedName("display_name")
        private String displayName;
        @SerializedName("linked_at")
        private Long linkedAt;
        @SerializedName("emote_capacity")
        private Integer emoteCapacity;
        @SerializedName("emote_set_id")
        private String emoteSetId;

        public Connection(String id, String platform, String username, String displayName, Long linkedAt, Integer emoteCapacity, String emoteSetId) {
            this.id = id;
            this.platform = platform;
            this.username = username;
            this.displayName = displayName;
            this.linkedAt = linkedAt;
            this.emoteCapacity = emoteCapacity;
            this.emoteSetId = emoteSetId;
        }

        public Connection() {
        }

        public String getId() {
            return id;
        }

        public String getPlatform() {
            return platform;
        }

        public String getUsername() {
            return username;
        }

        public String getDisplayName() {
            return displayName;
        }

        public Long getLinkedAt() {
            return linkedAt;
        }

        public Integer getEmoteCapacity() {
            return emoteCapacity;
        }

        public String getEmoteSetId() {
            return emoteSetId;
        }

        @Override
        public String toString() {
            return "Connection{" +
                    "id='" + id + '\'' +
                    ", platform='" + platform + '\'' +
                    ", username='" + username + '\'' +
                    ", displayName='" + displayName + '\'' +
                    ", linkedAt=" + linkedAt +
                    ", emoteCapacity=" + emoteCapacity +
                    ", emoteSetId='" + emoteSetId + '\'' +
                    '}';
        }
    }

    private String id;
    private String username;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("role_ids")
    private ArrayList<String> roleIds;
    private ArrayList<Connection> connections;

    public Actor(String id, String username, String displayName, String avatarUrl, ArrayList<String> roleIds, ArrayList<Connection> connections) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
        this.roleIds = roleIds;
        this.connections = connections;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public ArrayList<String> getRoleIds() {
        return roleIds;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", roleIds=" + roleIds +
                ", connections=" + connections +
                '}';
    }
}
