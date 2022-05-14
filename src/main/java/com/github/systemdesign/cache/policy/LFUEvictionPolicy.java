package com.github.systemdesign.cache.policy;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUEvictionPolicy<Key> implements EvictionPolicy<Key>{

    private final Map<Key, Integer> countMap;
    private final Map<Integer, LinkedHashSet<Key>> countItemListMap;
    private int min;

    public LFUEvictionPolicy() {
        this.countItemListMap = new HashMap<>();
        this.countMap = new HashMap<>();
        this.min = -1;
    }

    @Override
    public void keyAccessed(Key key) {
        if(countMap.containsKey(key)) {
            int count = countMap.get(key);
            countMap.put(key, count + 1);
            countItemListMap.get(count).remove(key);
            if(count == min && countItemListMap.get(count).size()==0) {
                min++;
                countItemListMap.remove(count);
            }
            if(!countItemListMap.containsKey(count+1))
                countItemListMap.put(count + 1, new LinkedHashSet<>());
            countItemListMap.get(count + 1).add(key);

        } else {
            min = 1;
            countMap.put(key, 1);
            if(!countItemListMap.containsKey(min))
                countItemListMap.put(min, new LinkedHashSet<>());
            countItemListMap.get(min).add(key);

        }
    }

    @Override
    public Key evictKey() {
        Key evictKey = countItemListMap.get(min).iterator().next();
        countMap.remove(evictKey);
        countItemListMap.get(min).remove(evictKey);
        return evictKey;
    }
}