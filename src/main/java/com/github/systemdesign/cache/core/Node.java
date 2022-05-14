package com.github.systemdesign.cache.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Node<T> {
    protected Node<T> prev;
    protected Node<T> next;
    protected T data;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev =  null;
    }
}
