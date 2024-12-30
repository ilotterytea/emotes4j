package com.github.ilotterytea.emotes4j.seventv.api.users;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author ilotterytea
 * @since 1.0
 */
public class SevenTVUser {
    private String id;
    private String username;
    @SerializedName("display_name")
    private String displayName;
    private List<UserConnection> connections;

    public SevenTVUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<UserConnection> getConnections() {
        return connections;
    }

    public void setConnections(List<UserConnection> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "SevenTVUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", connections=" + connections +
                '}';
    }
}
