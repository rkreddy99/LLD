package org.lld.eviction;

public interface EvictionPolicy<K> {
    void accessedKey(K key);

    K evictAndReturnKey();
}
