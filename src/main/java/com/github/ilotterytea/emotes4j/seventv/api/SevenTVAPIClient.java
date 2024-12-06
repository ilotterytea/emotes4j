package com.github.ilotterytea.emotes4j.seventv.api;

import com.github.ilotterytea.emotes4j.seventv.api.emotes.EmoteSet;
import com.github.ilotterytea.emotes4j.seventv.api.users.SevenTVUser;
import com.github.ilotterytea.emotes4j.seventv.api.users.User;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

public class SevenTVAPIClient {
    private static final Logger logger = LoggerFactory.getLogger(SevenTVAPIClient.class.getSimpleName());

    public static Optional<User> getUser(String userId) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url(String.format("https://7tv.io/v3/users/twitch/%s", userId))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 200 || response.body() == null) {
                return Optional.empty();
            }

            return Optional.ofNullable(new Gson().fromJson(response.body().string(), User.class));
        } catch (IOException e) {
            logger.error("Couldn't get a user: ", e);
            return Optional.empty();
        }
    }

    public static Optional<SevenTVUser> getSevenTVUser(String sevenTVId) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url(String.format("https://7tv.io/v3/users/%s", sevenTVId))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 200 || response.body() == null) {
                return Optional.empty();
            }

            return Optional.ofNullable(new Gson().fromJson(response.body().string(), SevenTVUser.class));
        } catch (IOException e) {
            logger.error("Couldn't get a user: ", e);
            return Optional.empty();
        }
    }

    public static Optional<EmoteSet> getEmoteSet(String seventvID) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url(String.format("https://7tv.io/v3/emote-sets/%s", seventvID))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 200 || response.body() == null) {
                return Optional.empty();
            }

            return Optional.ofNullable(new Gson().fromJson(response.body().string(), EmoteSet.class));
        } catch (IOException e) {
            logger.error("Couldn't get an emote set: ", e);
            return Optional.empty();
        }
    }
}
