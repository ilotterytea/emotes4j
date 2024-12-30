package com.github.ilotterytea.emotes4j.betterttv.emotes;

public class EmoteBase {
    protected String id;
    protected String code;

    public EmoteBase(String id, String code) {
        this.id = id;
        this.code = code;
    }

    public EmoteBase() {
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "EmoteBase{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
