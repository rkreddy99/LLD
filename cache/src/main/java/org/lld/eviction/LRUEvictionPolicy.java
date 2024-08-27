package org.lld.eviction;

import org.lld.datastructs.DoublyLinkedList;
import org.lld.datastructs.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {
    private DoublyLinkedList<Key> dll;
    private Map<Key, DoublyLinkedListNode<Key>> keyDoublyLinkedListNodeMap;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList<>();
        this.keyDoublyLinkedListNodeMap = new HashMap<>();
    }
    @Override
    public void accessedKey(Key key) {
        if (keyDoublyLinkedListNodeMap.containsKey(key)) {
            DoublyLinkedListNode<Key> node = keyDoublyLinkedListNodeMap.get(key);
            dll.detachNode(node);
            dll.addNodeInFront(node);
        } else {
            DoublyLinkedListNode<Key> node = dll.addKeyInFront(key);
            keyDoublyLinkedListNodeMap.put(key, node);
        }
    }

    @Override
    public Key evictAndReturnKey() {
        Key key = dll.removeLastNodeAndGetKey();
        keyDoublyLinkedListNodeMap.remove(key);
        return key;
    }

}
