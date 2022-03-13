package com.lowleveldesign.cache.eviction_policy;

import com.lowleveldesign.cache.exceptions.UnexpectedStateException;

/**
 * Interface for defining eviction policies.
 *
 * @param <Key> Type of key.
 */
public interface EvictionPolicy<Key> {
    /**
     * @param key to be accessed
     */
    void keyAccessed(Key key);

    /**
     * Evict key via eviction policy and return it.
     */
    Key evictKey() throws UnexpectedStateException;
}
