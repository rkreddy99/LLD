package org.lld.factory;

import org.lld.Cache;
import org.lld.eviction.LRUEvictionPolicy;
import org.lld.storage.HashMapStorage;

public class CacheFactory<Key, Value> {
    public Cache<Key, Value> getDefaultCache(int capacity) {
        return new Cache<>(
                new HashMapStorage<>(capacity),
                new LRUEvictionPolicy<>()
        );
    }
}
