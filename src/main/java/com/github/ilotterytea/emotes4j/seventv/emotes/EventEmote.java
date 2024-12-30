package com.github.ilotterytea.emotes4j.seventv.emotes;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class EventEmote {
    public static class EmoteValue {
        private String id;
        private String name;

        public EmoteValue(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public EmoteValue() {
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "EmoteValue{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Nullable
    @SerializedName("old_value")
    private EmoteValue oldValue;
    @Nullable
    @SerializedName("value")
    private EmoteValue value;

    public EventEmote(@Nullable EmoteValue oldValue, @Nullable EmoteValue value) {
        this.oldValue = oldValue;
        this.value = value;
    }

    public EventEmote() {
    }

    @Nullable
    public EmoteValue getValue() {
        return value;
    }

    @Nullable
    public EmoteValue getOldValue() {
        return oldValue;
    }

    @Override
    public String toString() {
        return "EventEmote{" +
                "oldValue=" + oldValue +
                ", value=" + value +
                '}';
    }
}
