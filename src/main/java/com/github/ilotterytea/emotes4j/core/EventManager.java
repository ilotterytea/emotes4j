package com.github.ilotterytea.emotes4j.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * A base class for event listeners.
 *
 * @author ilotterytea
 * @version 1.0
 */
public class EventManager {
    protected final ConcurrentHashMap<Class<? extends Event>, Consumer<? extends Event>> events;

    public EventManager() {
        this.events = new ConcurrentHashMap<>();
    }


    /**
     * Register an event with the method. Everytime when the event happens, the method will be called.
     *
     * @param event  Event.
     * @param method Method.
     */
    public <E extends Event> void onEvent(Class<E> event, Consumer<E> method) {
        this.events.put(event, method);
    }


    /**
     * Call an event with provided data.
     *
     * @param event Event type.
     * @param data  Data. It must have the same type as the event.
     * @param <E>   Event
     * @return true if the event was successfully called, otherwise if the event was not registered or event does not have the same type as data.
     */
    protected <E extends Event> boolean callEvent(Class<E> event, Object data) {
        if (!this.events.containsKey(event)) return false;

        if (event.isInstance(data)) {
            Consumer<E> consumer = (Consumer<E>) this.events.get(event);
            consumer.accept((E) data);
            return true;
        }

        return false;
    }
}
