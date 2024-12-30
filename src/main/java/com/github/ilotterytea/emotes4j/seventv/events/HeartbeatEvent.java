package com.github.ilotterytea.emotes4j.seventv.events;

import com.github.ilotterytea.emotes4j.core.Event;

public class HeartbeatEvent extends Event {
    private Integer count;

    public HeartbeatEvent(Integer count) {
        this.count = count;
    }

    public HeartbeatEvent() {
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "HeartbeatEvent{" +
                "count=" + count +
                '}';
    }
}
