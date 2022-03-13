package com.lowleveldesign.cache.cache_impl;

import com.lowleveldesign.cache.eviction_policy.EvictionPolicy;
import com.lowleveldesign.cache.exceptions.CacheFullException;
import com.lowleveldesign.cache.exceptions.InvalidKeyException;
import com.lowleveldesign.cache.exceptions.KeyNotFoundException;
import com.lowleveldesign.cache.exceptions.UnexpectedStateException;
import com.lowleveldesign.cache.storage.Storage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cache<Key, Value> {

    private final EvictionPolicy<Key> evictionPolicy;
    private final Storage<Key, Value> storage;

    public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    /**
     * Method to insert key,value pair in Cache.
     *
     * @param key   to be inserted
     * @param value to be inserted
     */
    public void put(Key key, Value value) {
        try {
            this.storage.insert(key, value);
            this.evictionPolicy.keyAccessed(key);
            log.info("{} -> {}: inserted successfully.", key, value);
        } catch (InvalidKeyException invalidKeyException) {
            log.error(invalidKeyException.getMessage());
        } catch (CacheFullException cacheFullException) {
            log.warn(cacheFullException.getMessage());
            evictAndPutKey(key, value);
        }
    }

    private void evictAndPutKey(Key key, Value value) {
        try {
            Key keyToRemove = evictionPolicy.evictKey();
            this.storage.remove(keyToRemove);
            put(key, value);
        } catch (UnexpectedStateException | KeyNotFoundException exception) {
            log.error(exception.getMessage());
        }
    }

    /**
     * Method to fetch value for a given key.
     *
     * @param key to be searched
     * @return Value of the given key.
     */
    public Value get(Key key) {
        try {
            Value value = this.storage.fetch(key);
            this.evictionPolicy.keyAccessed(key);
            return value;
        } catch (KeyNotFoundException keyNotFoundException) {
            log.info(keyNotFoundException.getMessage());
            return null;
        }
    }


}
