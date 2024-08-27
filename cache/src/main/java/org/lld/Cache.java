package org.lld;

import org.lld.eviction.EvictionPolicy;
import org.lld.exceptions.NotFoundException;
import org.lld.exceptions.StorageFullException;
import org.lld.storage.Storage;

public class Cache<Key, Value> {
    Storage<Key, Value> storage;
    EvictionPolicy<Key> evictionPolicy;

    public Cache(Storage<Key, Value> storage, EvictionPolicy<Key> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public Value get(Key key) {
        try {
            Value value = storage.get(key);
            evictionPolicy.accessedKey(key);
            return value;
        } catch (NotFoundException notFoundException) {
            System.out.println("Key not found");
            return null;
        }
    }

    public void put(Key key, Value value) {
        try {
            storage.put(key, value);
            evictionPolicy.accessedKey(key);
        } catch (StorageFullException storageFullException) {
            Key evictedKey = evictionPolicy.evictAndReturnKey();
            System.out.println("Creating space by evicting item..." + evictedKey);
            storage.remove(evictedKey);
            put(key, value);
        }
    }
}
