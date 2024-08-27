package org.lld.datastructs;

import lombok.Getter;

@Getter
public class DoublyLinkedList<Key> {
    DoublyLinkedListNode<Key> head;
    DoublyLinkedListNode<Key> tail;

    public DoublyLinkedList() {
        this.head = new DoublyLinkedListNode<>(null);
        this.tail = new DoublyLinkedListNode<>(null);
        head.next = tail;
        tail.prev = head;
    }

    public void detachNode(DoublyLinkedListNode<Key> node) {
        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void addNodeInFront(DoublyLinkedListNode<Key> node) {
        DoublyLinkedListNode<Key> tempNode = head.next;
        head.next = node;
        node.prev = head;
        tempNode.prev = node;
        node.next = tempNode;
    }

    public DoublyLinkedListNode<Key> addKeyInFront(Key key) {
        DoublyLinkedListNode<Key> node = new DoublyLinkedListNode<>(key);
        addNodeInFront(node);
        return node;
    }

    public DoublyLinkedListNode<Key> getKeyAtLast() {
        if (isEmpty()) return null;
        return tail.prev;
    }

    public Key removeLastNodeAndGetKey() {
        if (isEmpty()) return null;
        DoublyLinkedListNode<Key> nodeToRemove = tail.prev;
        detachNode(nodeToRemove);
        return nodeToRemove.getKey();
    }

    public boolean isEmpty() {
        return head.next == tail;
    }
}
