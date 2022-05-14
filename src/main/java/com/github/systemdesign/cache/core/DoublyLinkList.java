package com.github.systemdesign.cache.core;

import com.github.systemdesign.cache.exception.InvalidElementException;
import com.github.systemdesign.cache.exception.NotFoundException;

import java.util.Objects;

public class DoublyLinkList<T> {
    protected Node<T> head;
    protected Node<T> tail;

    public DoublyLinkList() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
    }
    public boolean isEmpty() {
        return head.next == tail;
    }

    public Node<T> addNodeAtLast(Node node) {
        Node tailPrev = tail.prev;
        tailPrev.next = node;
        node.next = tail;
        tail.prev = node;
        node.prev = tailPrev;
        return node;
    }

    public Node<T> addElementAtLast(T data) {
        if(Objects.isNull(data))
            throw new InvalidElementException();
        Node<T> node = new Node<>(data);
        return addNodeAtLast(node);
    }

    public Node getFirstNode() {
        if(isEmpty())
            throw new NotFoundException("No Node found");
        return head.next;
    }

    public Node getLastNode() {
        if(isEmpty())
            throw new NotFoundException("No Node found");
        return tail.prev;
    }

    public void deleteNode(Node node) {
        if(Objects.nonNull(node)) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
}
