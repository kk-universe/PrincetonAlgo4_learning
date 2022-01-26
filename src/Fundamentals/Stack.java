package Fundamentals;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;   //size of the stack

    //helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    //Initializes an empty stack
    public Stack() {
        first = null;
        n = 0;
    }

    //returns true if this stack is empty
    public boolean isEmpty() {
        return first == null;
    }

    //returns the number of items in this stack
    public int size() {
        return n;
    }

    //Adds the item to this stack
    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    //removes and returns the item most recently added  to this stack
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underFlow");
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    //returns(but does not remove ) the item most recently added to this stack.
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    //an iterator , doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
