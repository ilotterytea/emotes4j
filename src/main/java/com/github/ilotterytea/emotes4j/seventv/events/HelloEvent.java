package com.github.ilotterytea.emotes4j.seventv.events;

import com.github.ilotterytea.emotes4j.core.Event;
import com.google.gson.annotations.SerializedName;

public class HelloEvent extends Event {
    @SerializedName("heartbeat_interval")
    private Integer heartbeatInterval;
    @SerializedName("subscription_limit")
    private Integer subscriptionLimit;
    @SerializedName("session_id")
    private String sessionId;

    public HelloEvent(Integer heartbeatInterval, Integer subscriptionLimit, String sessionId) {
        this.heartbeatInterval = heartbeatInterval;
        this.subscriptionLimit = subscriptionLimit;
        this.sessionId = sessionId;
    }

    public HelloEvent() {

    }

    public Integer getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public Integer getSubscriptionLimit() {
        return subscriptionLimit;
    }

    public String getSessionId() {
        return sessionId;
    }

    @Override
    public String toString() {
        return "HelloEvent{" +
                "heartbeatInterval=" + heartbeatInterval +
                ", subscriptionLimit=" + subscriptionLimit +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
