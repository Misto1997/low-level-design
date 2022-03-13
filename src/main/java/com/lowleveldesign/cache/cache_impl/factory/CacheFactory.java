package com.lowleveldesign.cache.cache_impl.factory;

import com.lowleveldesign.cache.cache_impl.Cache;
import com.lowleveldesign.cache.eviction_policy.LRUCacheEvictionPolicyImpl;
import com.lowleveldesign.cache.storage.StorageImpl;

public class CacheFactory<Key, Value> {

    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<>(new LRUCacheEvictionPolicyImpl<>(),
                new StorageImpl<>(capacity));
    }
}
