import java.util.Iterator;
import java.util.NoSuchElementException;
/**
    LinkedList Generic Class
 */
public class LinkedList<E> {
    public static int containsIterations, removeIterations, addIterations;
    // Data members
    private Node head, tail;
    private int size;
    /**
        Inner class Node
    */
    private class Node {
        E value;
        Node next;
        Node(E initialValue) {
            value = initialValue;
            next = null;
        }
    }
    /**
        Default Constructor
        creates an empty linkedlist
    */
    public LinkedList() {
        head = tail = null;
        size = 0;
    }
    /**
        Adding a value at the head of the list
        @param value to be added
        @return true if the operation was successful
    */
    public boolean addFirst(E value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        }
        else {
            newNode.next = head;
            head = newNode;
        }
        size++;
        return true;
    }
    /**
        Adding a value at the tail of the list
        @param value to be added
        @return true if the operation was successful
    */
    public boolean addLast(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        }
        else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }
    /**
        Adding a value at the tail of the list
        calls addLast
        @param value to be added
        @return true if the operation was successful
    */
    public boolean add(E item) {
        return addLast(item);
    }
    /**
        Get the value of the node at the head of the list
        @return value of the node at the head
        @throws NoSuchElementException if the list is empty
    */
    public E getFirst() {
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }
    /**
        Get the value of the node at the tail of the list
        @return value of the node at the tail
        @throws NoSuchElementException if the list is empty
     */
    public E getLast() {
        if (head == null)
            throw new NoSuchElementException();
        return tail.value;
    }
    /**
        Removes the node at the head of the list
        @return true if the remove operation was successful and decrements the size
        @throws NoSuchElementException if the list is empty
     */
    public boolean removeFirst() {
        if (head == null)
            throw new NoSuchElementException();
        head = head.next;
        if (head == null) // removed the only node in the LL
            tail = null;
        size--;
        return true;
    }
    /**
        Removes the node at the tail of the list
        @return true if the remove operation was successful and decrements the size
        @throws NoSuchElementException if the list is empty
    */
    public boolean removeLast() {
        if (head == null)
            throw new NoSuchElementException();
        if (size == 1)
            return removeFirst();
        Node current = head;
        while(current.next != tail){
            current = current.next;
        }
        current.next = null;
        tail = current;
        size--;
        return true;
    }
    /**
        toString method
        @return a formatted string that contains the values of all the nodes in the list
    */
    public String toString() {
        String output = "[";
        Node node = head;
        while (node != null) {
            output += node.value + " ";
            node = node.next;
        }
        output += "]";
        return output;
    }
    /**
        clear method
        restes size to 0 and head and tail to null
    */
    public void clear() {
        head = tail = null;
        size = 0;
    }
    /**
        isEmpty method
        @return true if the list is empty
    */
    public boolean isEmpty() {
        return (size == 0);
    }
    /**
        size method
        @return the number of nodes in the list
    */
    public int size() {
        return size;
    }

    /**
        iterator method
        @override iterator() from the interface Collection
        @return an iterator object pointing to the first value in the list
    */
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }
    /**
        Inner class that implements the interface Iterator
    */
    private class LinkedListIterator implements Iterator<E> {
        private Node current = head;
        /**
            hasNext method
            @return true if the current is not null
         */
        public boolean hasNext() {
            return (current != null);
        }
        /**
            next method
            @return the value of the node referenced by current and 
                    modifies current to hold the reference of the next node
            @throws NoSuchElementException if current is null
         */
        public E next() {
            if (current == null)
                throw new NoSuchElementException();
            E value = current.value;
            current = current.next;
            return value;
        }
    }
    public boolean contains(Object o){
        containsIterations = 0;
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            containsIterations++;
            E element = iter.next();
            if(element.equals(0)){
                return true;
            }

        }
        return false;}
        public boolean remove (Object o){
            removeIterations = 0;
            Node current = head;
            Node previous = null;
            while(current != null && !current.value.equals(o)){
                removeIterations++;
                previous = current;
                current = current.next;
            }
            if (current == null){
                return false;
            }
            if(previous == null){
                return removeFirst();
            }
            previous.next = current.next;
            size--;
            return true;
        }
        public boolean add(int index, E item){
            addIterations = 0;
            if(index<0 || index>size){
                throw new ArrayIndexOutOfBoundsException();
            }
            if(index == 0){
                return addFirst(item);
            }
            if(index == size){
                return addLast(item);
            }
            Node current = head;
            Node previous  = null;
            for(int i = 0; i<index; i++){
                addIterations++;
                previous = current;
                current = current.next;
            }
            Node newNode = new Node(item);
            previous.next = newNode;
            newNode.next = current;
            size++;
            return true;
        

        }
}