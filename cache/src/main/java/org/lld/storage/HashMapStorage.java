package org.lld.storage;

import org.lld.exceptions.NotFoundException;
import org.lld.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<Key, Value> implements Storage<Key, Value> {
    private final Map<Key, Value> storage;
    private final int capacity;

    public HashMapStorage(int capacity) {
        this.storage = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public void put(Key key, Value value) throws StorageFullException {
        if (!storage.containsKey(key) && isStorageFull()) throw new StorageFullException("Storage is full");
        storage.put(key, value);
    }

    @Override
    public Value get(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException(key + " not found in cache");
        return storage.get(key);
    }

    @Override
    public void remove(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException(key + " not found in cache");
        storage.remove(key);
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }
}
