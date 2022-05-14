package com.github.systemdesign.cache.storage;

import com.github.systemdesign.cache.exception.NotFoundException;
import com.github.systemdesign.cache.exception.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<Key, Value> implements Storage<Key, Value>{

    private Map<Key, Value> storage;
    private final Integer capacity;

    public HashMapStorage(Integer capacity) {
        this.capacity = capacity;
        storage = new HashMap<>();
    }


    @Override
    public Value get(Key key) {
        if(storage.containsKey(key))
            return storage.get(key);
        throw new NotFoundException(key + " Not found in storage");
    }

    @Override
    public void put(Key key, Value value) {
        if(isFull())
            throw new StorageFullException("Storage full");
        storage.put(key, value);
    }

    @Override
    public void remove(Key key) {
        if(!storage.containsKey(key))
            throw new NotFoundException(key + " Not found in storage");
        storage.remove(key);
    }

    private boolean isFull() {
        return storage.size()  == capacity;
    }
}
