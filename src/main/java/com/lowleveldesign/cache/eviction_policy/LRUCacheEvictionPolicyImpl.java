package com.lowleveldesign.cache.eviction_policy;

import com.lowleveldesign.cache.eviction_policy.algorithm.DoublyLinkedList;
import com.lowleveldesign.cache.eviction_policy.algorithm.DoublyLinkedListNode;
import com.lowleveldesign.cache.exceptions.UnexpectedStateException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Eviction policy based on LRU(Least Recent Used) Cache algorithm.
 *
 * @param <Key> Key type.
 */
@Slf4j
public class LRUCacheEvictionPolicyImpl<Key> implements EvictionPolicy<Key> {

    private final DoublyLinkedList<Key> lruDLL;
    private final Map<Key, DoublyLinkedListNode<Key>> mapper;

    public LRUCacheEvictionPolicyImpl() {
        this.lruDLL = new DoublyLinkedList<>();
        this.mapper = new HashMap<>();
    }

    @Override
    public void keyAccessed(Key key) {
        if (mapper.containsKey(key)) {
            lruDLL.removeNode(mapper.get(key));
            lruDLL.addNodeAtFirst(mapper.get(key));
        } else {
            DoublyLinkedListNode<Key> newNode = lruDLL.addNode(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public Key evictKey() throws UnexpectedStateException {
        DoublyLinkedListNode<Key> last = lruDLL.getLastNode();
        if (last == null) {
            throw new UnexpectedStateException("Storage full and no key to evict.");
        }
        lruDLL.removeNode(last);
        mapper.remove(last.getKey());
        log.info("Key: " + last.getKey() + " evicted successfully.");
        return last.getKey();
    }
}
