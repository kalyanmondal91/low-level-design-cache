package com.github.systemdesign.cache.policy;

public interface EvictionPolicy<Key> {
    void keyAccessed(Key key);
    Key evictKey();
}
