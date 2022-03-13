package com.lowleveldesign.cache.storage;

import com.lowleveldesign.cache.exceptions.CacheFullException;
import com.lowleveldesign.cache.exceptions.InvalidKeyException;
import com.lowleveldesign.cache.exceptions.KeyNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class StorageImpl<Key, Value> implements Storage<Key, Value> {

    Map<Key, Value> storageMap;
    private final Integer capacity;

    public StorageImpl(Integer capacity) {
        this.capacity = capacity;
        storageMap = new HashMap<>();
    }

    @Override
    public void insert(Key key, Value value) throws CacheFullException, InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("Invalid key Type: null key is not allowed.");
        }
        if (isStorageFull()) {
            throw new CacheFullException("Cache is full. eviction policy is in process.");
        }
        storageMap.put(key, value);
    }

    @Override
    public void remove(Key key) throws KeyNotFoundException {
        if (!storageMap.containsKey(key)) {
            throw new KeyNotFoundException("Key: " + key + " doesn't exist in cache.");
        }
        storageMap.remove(key);
    }

    @Override
    public Value fetch(Key key) throws KeyNotFoundException {
        if (!storageMap.containsKey(key)) {
            throw new KeyNotFoundException("key " + key + " doesn't exist in cache.");
        }
        return storageMap.get(key);
    }

    private boolean isStorageFull() {
        return storageMap.size() == capacity;
    }
}
