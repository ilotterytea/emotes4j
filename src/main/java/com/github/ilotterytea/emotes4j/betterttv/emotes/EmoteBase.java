package com.github.ilotterytea.emotes4j.betterttv.emotes;

public class EmoteBase {
    private String id;
    private String code;

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
}
