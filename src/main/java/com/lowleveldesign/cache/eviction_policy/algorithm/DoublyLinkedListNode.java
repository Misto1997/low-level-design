package com.lowleveldesign.cache.eviction_policy.algorithm;

import lombok.Getter;

/**
 * DoublyLinkedList Node creation.
 *
 * @param <Key> Type of element to be inserted.
 */
@Getter
public class DoublyLinkedListNode<Key> {

    DoublyLinkedListNode<Key> next;
    DoublyLinkedListNode<Key> prev;
    Key key;

    public DoublyLinkedListNode() {

    }

    public DoublyLinkedListNode(Key key) {
        this.key = key;
        this.next = null;
        this.prev = null;
    }
}
