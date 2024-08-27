package org.lld.storage;

import org.lld.exceptions.NotFoundException;
import org.lld.exceptions.StorageFullException;

public interface Storage<Key, Value> {
    void put(Key key, Value value) throws StorageFullException;
    Value get(Key key) throws NotFoundException;
    void remove(Key key) throws NotFoundException;
}
