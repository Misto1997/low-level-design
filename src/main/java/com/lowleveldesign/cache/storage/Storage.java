package com.lowleveldesign.cache.storage;

import com.lowleveldesign.cache.exceptions.CacheFullException;
import com.lowleveldesign.cache.exceptions.InvalidKeyException;
import com.lowleveldesign.cache.exceptions.KeyNotFoundException;

public interface Storage<Key, Value> {
    void insert(Key key, Value value) throws CacheFullException, InvalidKeyException;

    void remove(Key key) throws KeyNotFoundException;

    Value fetch(Key key) throws KeyNotFoundException;
}
