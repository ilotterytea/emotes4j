# emotes4j

A Java library for getting global emotes as well as channel emotes. Clients for Event APIs are included!

It supports [7TV](https://7tv.app) and [BetterTTV](https://betterttv.com) for now.
In future there will be more providers.

## Features

- [ ] 7TV EventAPI *(not fully implemented)*
- [ ] BetterTTV WebSocket *(not fully implemented)*
- [x] BetterTTV API
- [ ] 7TV API *(not fully implemented)*
- [ ] Twitch support
- [ ] FrankerFaceZ support
- [ ] Ability to create, update and remove emotes

## Installation guide

This library can be retrieved from JitPack.

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.ilotterytea:emotes4j:0.1.0'
}
```

## Examples

### Connecting to 7TV EventAPI

```java
public MyApp() throws InterruptedException {
    SevenTVEventClient stvClient = new SevenTVEventClient();
    stvClient.getClient().connectBlocking();
    stvClient.getEventManager().onEvent(EmoteSetUpdateEvent.class, this::handleSTVEmoteSetUpdate);

    BetterTTVEventClient bttvClient = new BetterTTVEventClient();
    bttvClient.getClient().connectBlocking();
    bttvClient.getEventManager().onEvent(EmoteUpdateCreate.class, this::handleBTTVEmoteCreate);
}

private void handleSTVEmoteSetUpdate(EmoteSetUpdateEvent event) {
    // Do something with the event...
}

private void handleBTTVEmoteCreate(EmoteCreateEvent event) {
    // Do something with the event...
}
```

###   

### Dependencies

+ `gson` for JSON deserialization
+ `java-websocket` for Event API connections
+ `okhttp` for API calls
+ `logback` for logging