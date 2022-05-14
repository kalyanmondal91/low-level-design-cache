package com.github.systemdesign.cache.factory;

import com.github.systemdesign.cache.Cache;
import com.github.systemdesign.cache.policy.EvictionPolicy;
import com.github.systemdesign.cache.policy.LFUEvictionPolicy;
import com.github.systemdesign.cache.policy.LRUEvictionPolicy;
import com.github.systemdesign.cache.storage.HashMapStorage;
import com.github.systemdesign.cache.storage.Storage;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CacheFactory<Key> {

    public Cache cache(final int capacity, final String policy) {
        return new Cache(evictionPolicy(policy), storage(capacity));
    }
    private Storage storage(final int capacity) {
        return new HashMapStorage(capacity);
    }

    private EvictionPolicy evictionPolicy(String policy) {
        if(Objects.isNull(policy)) {
            return null;
        } else if("LRU".equalsIgnoreCase(policy)) {
            return new LRUEvictionPolicy<Key>();
        } else if("LFU".equalsIgnoreCase(policy)) {
            return new LFUEvictionPolicy<Key>();
        }
        return null;
    }
}
