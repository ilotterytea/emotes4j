package com.github.ilotterytea.emotes4j.betterttv;

import com.github.ilotterytea.emotes4j.betterttv.badges.BadgeUser;
import com.github.ilotterytea.emotes4j.betterttv.emotes.Emote;
import com.github.ilotterytea.emotes4j.betterttv.users.BetterTTVUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BetterTTVAPIClient {
    private static final String BASE_URL = "https://api.betterttv.net/3";

    public static Optional<BetterTTVUser> getTwitchUser(String id) {
        return getUser("twitch", id);
    }

    public static Optional<BetterTTVUser> getUser(String provider, String providerId) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url(String.format("%s/cached/users/%s/%s", BASE_URL, provider, providerId))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 200 || response.body() == null) {
                return Optional.empty();
            }

            return Optional.ofNullable(new Gson().fromJson(response.body().string(), BetterTTVUser.class));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static Optional<List<Emote>> getGlobalEmotes() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url(String.format("%s/cached/emotes/global", BASE_URL))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 200 || response.body() == null) {
                return Optional.empty();
            }

            return Optional.ofNullable(new Gson().fromJson(response.body().string(), new TypeToken<List<Emote>>() {
            }.getType()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static Optional<List<BadgeUser>> getTwitchBadgeUsers() {
        return getBadgeUsers("twitch");
    }

    public static Optional<List<BadgeUser>> getBadgeUsers(String providerId) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url(String.format("%s/cached/badges/%s", BASE_URL, providerId))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 200 || response.body() == null) {
                return Optional.empty();
            }

            return Optional.ofNullable(new Gson().fromJson(response.body().string(), new TypeToken<List<BadgeUser>>() {
            }.getType()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

}
