package com.github.systemdesign.cache;

import com.github.systemdesign.cache.exception.StorageFullException;
import com.github.systemdesign.cache.policy.EvictionPolicy;
import com.github.systemdesign.cache.storage.Storage;

public class Cache<Key, Value> {
    private final EvictionPolicy<Key> evictionPolicy;
    private final Storage<Key, Value> storage;

    public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public void put(Key key, Value value) {
        try {
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
        } catch (StorageFullException exception) {
            Key keyToRemove = evictionPolicy.evictKey();
            storage.remove(keyToRemove);
            put(key, value);
        }
    }

    public Value get(Key key) {
        Value value = storage.get(key);
        evictionPolicy.keyAccessed(key);
        return value;
    }
}
