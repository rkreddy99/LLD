package org.lld.datastructs;

import lombok.Getter;

@Getter
public class DoublyLinkedListNode<Key> {
    DoublyLinkedListNode<Key> next;
    DoublyLinkedListNode<Key> prev;
    Key key;

    public DoublyLinkedListNode(Key key) {
        this.key = key;
        this.next = null;
        this.prev = null;
    }
}
