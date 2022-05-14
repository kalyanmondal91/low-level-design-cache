package com.github.systemdesign.cache.policy;

import com.github.systemdesign.cache.core.DoublyLinkList;
import com.github.systemdesign.cache.core.Node;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key>{

    private final DoublyLinkList<Key> dll;
    private final Map<Key, Node<Key>> map;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkList<>();
        this.map = new HashMap<>();
    }

    @Override
    public void keyAccessed(Key key) {
        if(map.containsKey(key)) {
            dll.deleteNode(map.get(key));
            dll.addNodeAtLast(map.get(key));
        } else {
            Node node = dll.addElementAtLast(key);
            map.put(key, node);
        }
    }

    @Override
    public Key evictKey() {
        Node<Key> first = dll.getFirstNode();
        map.remove(first.getData());
        dll.deleteNode(first);
        return first.getData();
    }
}
