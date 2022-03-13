package com.lowleveldesign.cache.eviction_policy.algorithm;

/**
 * DLL(DoublyLinkedList) to implement LRU(Least Recent Used) Based Cache
 * <p>
 * Visualization will look like this:
 * Initialization: HeadNode -> TailNode
 * After Insertion: HeadNode -> 1 -> 2 -> 3 -> TailNode
 *
 * @param <Key> Key of element to be inserted.
 */

public class DoublyLinkedList<Key> {
    DoublyLinkedListNode<Key> head;
    DoublyLinkedListNode<Key> tail;

    public DoublyLinkedList() {
        head = new DoublyLinkedListNode<>();
        tail = new DoublyLinkedListNode<>();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Removes node from its position from DLL.
     *
     * @param node Node to be removed.
     */
    public void removeNode(DoublyLinkedListNode<Key> node) {
        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    /**
     * Adds node after Head Node.
     *
     * @param node Node to be added.
     */
    public void addNodeAtFirst(DoublyLinkedListNode<Key> node) {
        DoublyLinkedListNode<Key> headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;
    }

    /**
     * Checks and Adds node at first.
     *
     * @param key Element to be added.
     * @return Reference to new node created for the element.
     */
    public DoublyLinkedListNode<Key> addNode(Key key) {
        DoublyLinkedListNode<Key> node = new DoublyLinkedListNode<>(key);
        addNodeAtFirst(node);
        return node;
    }


    /**
     * Check's if DLL is empty or not
     *
     * @return true(DLL is empty)/false(DLL is not empty)
     */
    public boolean isDLLEmpty() {
        return head.next == tail;
    }

    /**
     * Fetch last node from DLL.
     *
     * @return last node or null if DLL is empty.
     */
    public DoublyLinkedListNode<Key> getLastNode() {
        if (isDLLEmpty()) {
            return null;
        }
        return tail.prev;
    }
}
