import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Comparator;
/**
    LinkedList Generic Class
 */
public class LinkedList<E> implements List<E>, Cloneable{
    // Data members
    private Node head, tail;
    private int size;
    /**
        Inner class Node
    */
    private class Node {
        //doubly link
        //add number previous that has type node
        E value;
        Node next, previous; ////// add a data member previous for the backward links
        Node(E initialValue) {
            value = initialValue;
            next = previous = null;
        }
    }
    /**
     * Default Constructor
     * creates an empty linkedlist
    */
    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    /**
     * Get the size of this list
     * @return the number of elements in this list
    */
    public int size() {
        return size;
    }

    /**
     * clears the list
     * resets the size to 0 and head and tail to null
    */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * check if the list is empty
     * @return true if the list is empty, false otherwise
    */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Adding a value at the tail of the list
     * calls addLast
     * @param item to be added
     * @return true if the operation was successful
    */
    public boolean add(E item) {
        addLast(item);
        return true;
    }

    /**
     * Adding a value at the head of the list
     * @param value to be added
     * @return true if the operation was successful
    */
    public void addFirst(E value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        }
        else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Adding a value at the tail of the list
     * @param value to be added
     * @return true if the operation was successful
    */
    public void addLast(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        }
        else{
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Method to add a new item at a given position index
     * @param index the position where item should be added
     * @param item the value of the element to be added
     * @return true if item was added successfully, false otherwise
     * @throws ArrayIndexOutOfBoundsException if index < 0 or index > size
    */
    public void add(int index, E item){
        if(index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(index == 0){
            addFirst(item);
        }
        else if (index == size){
            addLast(item);
        }
        else{
            Node current = head;
            Node previous = null;
            for(int i=0; i<index; i++){
                previous = current;
                current = current.next;
            }
            Node newNode = new Node(item);
            previous.next = newNode;
            current.previous = newNode;
            newNode.previous = previous;
            newNode.next = current;
            size++;
        }
    }

    /**
     * Linear search method
     * @param o the object being searched
     * @return true if o was found in this list, false otherwise
     */
    public boolean contains(Object o){
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            E element = iter.next();
            if(element.equals(o)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * finds the position of a given object in this list
     * @param o the object being looked up
     * @return the index of o if found, -1 if o is not found in this list
     */
    public int indexOf(Object o){
        Iterator<E> iter = iterator();
        int index = 0;
        while (iter.hasNext()) {
            E element = iter.next();
            if (element.equals(o)) {
                return index;
            }
            index++;
        }
        return -1;
    }
    /**
     * finds the position of the last occurrence of a given object in this list
     * @param o the object being looked up
     * @return the last index of o if found, -1 if o is not found in this list
     */
    public int lastIndexOf(Object o){
        int index = size - 1;
        Node current = tail;
        while (current != null) {
            if (current.value.equals(o)) {
                return index;
            }
            current = current.previous;
            index--;
        }
        return -1;
    }

    /**
     * Get the value of the element at the given index
     * @return the value of the element at the position index
     * @throws ArrayIndexOutOfBoundsException if the index is not valid
     */
    public E get(int index){
        // check the index first
        // set current to head 
        // use a for loop from 0 to index to move current to the node at position index
        // after the loop, return the value of the node current
        if(index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException();
        Node current = head;
        if(index < size-1){
            for(int i = 0; i < index; i++)
                current = current.next;
        }else{
            current = tail;
        }
        return current.value;
    }

    /**
     * Set the value of the element at index
     * @param index of the element being modified
     * @param value new value of the element at index
     * @return the old value of the element at index
     * @throws ArrayIndexOutofBounds if index < 0 or index >= size
     */
    public E set(int index, E newVal){
        // check the index first
        // set current to head 
        // use a for loop from 0 to index to move current to the node at position index
        // after the loop, save the old value of the node current
        // set the value of the node current to newVal
        // return the old value
        if(index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException();
        Node current;
        if(index < size-1){
            current = head;
            for(int i = 0; i < index; i++)
                current = current.next;
        }else{
            current = tail;
        }
        E oldVal = current.value;
        current.value = newVal;
        return oldVal;
    }
    /**
     * Get the value of the node at the head of the list
     * @return value of the node at the head
     * @throws NoSuchElementException if the list is empty
    */
    public E getFirst() {
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }

    /**
     * Get the value of the node at the tail of the list
     * @return value of the node at the tail
     * @throws NoSuchElementException if the list is empty
     */
    public E getLast() {
        if (head == null)
            throw new NoSuchElementException();
        return tail.value;
    }
    /**
     * Removes the node at the head of the list
     * @return true if the remove operation was successful and decrements the size
     * @throws NoSuchElementException if the list is empty
     */
    public E removeFirst() {
        if (head == null)
            throw new NoSuchElementException();
        E val = head.value;
        head = head.next;
        if (head == null){ // removed the only node in the LL
            tail = null;
        }else{
            head.previous = null;
        }
        size--;
        return val;
    }
    /**
     * Removes the node at the tail of the list
     * @return true if the remove operation was successful and decrements the size
     * @throws NoSuchElementException if the list is empty
    */
    public E removeLast() {
        if (head == null)
            throw new NoSuchElementException();
        if (size == 1)
            return removeFirst();
        E value = tail.value;
        tail = tail.previous;
        tail.next = null;
        size--;
        return value;
    }
    /**
     * Remove an object o from the list
     * @param o the object to be removed
     * @return true if o was found and removed, false if o not found
     */
    public boolean remove(Object o){
        Node current = head;
        Node previous = null;
        while(current != null  && !current.value.equals(o)){
            previous = current;
            current = current.next;
        }
        if(current == null){
            return false;
        }
        if(previous == null){
            removeFirst();
            return true;
        }if(current==tail){
            removeLast();
            return true;
        }
        previous.next = current.next;
        current.next.previous = previous;
        size--;
        return true;
    }

    /**
     * Remove the element at a given index
     * @param index the position of the element to be removed
     * @return true if the elements was removed successfully, false otherwise
     * @throws ArrayIndexOutofBoundsException if index < 0 or index >= size
     */
    public E remove(int index){
        if(index<0 || index>=size)
            throw new ArrayIndexOutOfBoundsException();
        if(index == 0)
            return removeFirst();
        if(index == size - 1)
            return removeLast();
        ///// current is a node, index is an int
        //// you need to move current from head to the position index
        //// to reach the node at position index
        Node current = head;
        Node previous = null;
        for(int i=0; i<index; i++){
            previous = current;
            current = current.next;
        }
        E val = current.value;
        previous.next = current.next;
        current.next.previous = previous;
        size--;
        return val;
    }


    /**
     * Adds the elements of the collection c to this list
     * @param c the collection to be added to this list
     * @return true if the addition was successful
     */
    public boolean addAll(Collection<? extends E> c){
        boolean modified = false;
        Iterator<? extends E> it = c.iterator();
        while(it.hasNext()){
            addLast(it.next());
            modified = true;
        }
        return modified;
    }

    /**
     * Checks if the given collection is contained in this colection
     * @param c the collection to find in this collection
     * @return true if c is cointained in this collection, false otherwise
     */
    public boolean containsAll(Collection<?> c){
        Iterator<?> iter = c.iterator();
        while(iter.hasNext()){
            if(!contains(iter.next()))
                return false;
        }
        return true;
    }

    /**
     * removes the elements of this list that are not in the collection c
     * @param c the collection to be compared to this list
     * @return true if the intersection was successful
     */
    public boolean retainAll(Collection<?> c){
        boolean modified = false;
        Node current = head;
        while(current != null){
            Node next = current.next;
            if(!c.contains(current.value)){
                remove(current.value);
                modified = true;
            }
            current = next;
        }
        return modified;
    }

    /**
     * removes the elements of this list that are in the collection c
     * @param c the collection to be compared to this list
     * @return true if the difference was successful
     */
    public boolean removeAll(Collection<?> c){
        boolean modified = false;
        Node current = head;
        while(current != null){
            Node next = current.next;
            if(c.contains(current.value)){
                remove(current.value);
                modified = true;
            }
            current = next;
        }
        return modified;
    }


    /**
     * toString method
     * @return a formatted string that contains the values of all the nodes in the list
    */
    public String toString() {
        if(size == 0)
            return "[]";
        String output = "[";
        Node node = head;
        while (node.next != null) {
            output += node.value + " ";
            node = node.next;
        }
        output += node.value + "]";
        return output;
    }
    
    /**
     * compares two lists for equality
     * @param o the list to be compared to this list
     * @return true if the two lists are identical, false otherwise
     */
    public boolean equals(Object o){
        if(o instanceof LinkedList){
            LinkedList <E> ll = (LinkedList)o;
            if(this.size != ll.size)
                return false;
            Iterator <E> iter1 = this.iterator();
            Iterator <E> iter2 = ll.iterator();
            while(iter1.hasNext() && iter2.hasNext()){
                if(!iter1.next().equals(iter2.next())){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    
    /**
     * Get the elements of this list in an array
     * @return array of objects
     */
    public Object[] toArray(){
        Object[] arr = new Object[size];
        int i = 0;
        for(Node current = head; current != null; current = current.next){
            arr[i++] = current.value;
        }
        return arr;
    }
    
    /**
     * sorts the elements of this list using the natural ordering of E or a comparator
     * @param c the comparator to be used for sorting if c is not null
     */
    public void sort(Comparator<? super E> c){
        // Selection sort: swap node values.
        for(Node i = head; i != null; i = i.next){
            Node min = i;
            for(Node j = i.next; j != null; j = j.next){
                int cmp;
                if(c != null) //// the comparator name is c (the parameter of the function)
                    cmp = c.compare(j.value, min.value);
                else {
                    Comparable<E> cmp1 = (Comparable<E>) j.value;
                    cmp = cmp1.compareTo(min.value);
                }
                if(cmp < 0)
                    min = j;
            }
            E temp = i.value;
            i.value = min.value;
            min.value = temp;
        }
    }

    /**
     * Get an iterator for this list
     * @return an iterator object pointing to the first value in the list
     * Time complexity: O(1)
    */
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Get a list iterator for this list starting at the beginning of the list
     * @return a list iterator object starting at the head of the list
     */
    public ListIterator<E> listIterator() {
        return new LinkedListListIterator();
    }

    /**
     * Get a list iterator for this list starting at the position index in the list
     * @return a list iterator object starting at the given index
     */
    public ListIterator<E> listIterator(int index) {
        return new LinkedListListIterator(index);
    }

    /**
     * overrides clone() for LinkedList
     * @return a deep copy of this list
     */
    public Object clone(){
            LinkedList<E> cloned = new LinkedList<>();
            Iterator <E> iter = this.iterator();
            while(iter.hasNext()){
                cloned.addLast(iter.next());
            }
            return cloned;
    }

     /**
     * Method to return a reversed list of this list
     * @return a new list that contains this list reversed
     */
    public List<E> reversed(){
        LinkedList<E> rev = new LinkedList<>();
        for(Node current = tail; current != null; 
            current = current.previous){
            rev.addLast(current.value);
        }
        return rev;
    }
    /**
     * Method to extract a sublist from this list
     * @param from the index where the sublist should start (inclusive)
     * @param to the index where the sublit should end (exclusive)
     * @return the sublist containing the elements from the index from to the index to-1
     *         an empty list if from >= to
     * @throws ArrayIndexOutOfBoundsException if from or to are out of bounds 
     */
    public List<E> subList(int from, int to){
    // Check for valid bounds
    if(from < 0 || to > size)
        throw new ArrayIndexOutOfBoundsException("subList indices out of bounds");
    
    // Return empty list if the starting index is not less than the ending index
    if(from >= to)
        return new LinkedList<E>();
    
    // Create a new LinkedList to hold the sublist
    LinkedList<E> sub = new LinkedList<>();
    // Traverse from the head to the 'from' index
    Node current = head;
    for (int i = 0; i < from; i++) {
        current = current.next;
    }
    // Add all elements from index 'from' (inclusive) to index 'to' (exclusive)
    for (int i = from; i < to; i++) {
        sub.addLast(current.value);
        current = current.next;
    }
    return sub;
    }

    /************************************************************
     * Inner class that implements the interface Iterator       *
     ************************************************************/
    private class LinkedListIterator implements Iterator<E> {
        private Node current = head;
        /**
         * hasNext method
         * @return true if the current is not null
         */
        public boolean hasNext() {
            return (current != null);
        }

        /**
         * next method
         * @return the value of the node referenced by current and 
            modifies current to hold the reference of the next node
         * @throws NoSuchElementException if current is null
         */
        public E next() {
            if (current == null)
                throw new NoSuchElementException();
            E value = current.value;
            current = current.next;
            return value;
        }
    }
    
    /*************************************************************
     * Inner class that implements the interface ListIterator    *
     *************************************************************/
    private class LinkedListListIterator implements ListIterator<E> {
        private Node current;
        private Node previous;
        //use two variables Node current and previous
        /**
         * Default constructor
         */
        public LinkedListListIterator(){
            //initialize current to head and previous to null
            current = head;
            previous = null;
            
        }
        /**
         * Constructor to initialize current to index
         * @param index the position where the iterator starts
         */
        public LinkedListListIterator(int index){
            //validate the index any method that accepts
            //previous to null
            if(index<0 || index>size)
                throw new ArrayIndexOutOfBoundsException();
            current = head;
            previous = null;
            for(int i=0; i<index; i++){
                previous = current;
                current = current.next;
            }

        }
        /**
         * checks if the iterator has a next element in the list
         * @return true if the end of the list is not reached
         */
        public boolean hasNext() {
            //current!=null, return true
            return current != null;
        }
        /**
         * get the next element
         * @return the value of the next element pointed to by the iterator
         * @throws NoSuchElementException if no next element can be accessed
         */
        public E next() {
            //save if value of current = next.current
            if(current == null)
                throw new NoSuchElementException();
            E value = current.value;
            previous = current;
            current = current.next;
            return value;
        }
        /**
         * checks if the iterator has a previous element in the list
         * @return true if the beginning of the list is not reached
         */
        public boolean hasPrevious() {
            //same but will check data member previous
            return previous!=null;
        }
        /**
         * get the previous element
         * @return the value of the element element pointed to by the iterator
         * @throws NoSuchElementException if no previous element can be accessed
         */
        public E previous() {
            //if it is not null, save value of previous, go backward
            if(previous == null)
                throw new NoSuchElementException();
            E value = previous.value;
            current = previous;
            previous = previous.previous;
            return value;
        }
        public void add(E e){
            throw new UnsupportedOperationException();
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
        public int nextIndex(){
            throw new UnsupportedOperationException();
        }
        public int previousIndex(){
            throw new UnsupportedOperationException();
        }
        public void set(E e){
            throw new UnsupportedOperationException();
        }
    }
    
}