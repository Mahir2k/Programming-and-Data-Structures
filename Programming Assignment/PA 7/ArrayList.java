import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.ListIterator;
/**
    Generic class to impelment an array based list
 */
public class ArrayList<E> implements List<E>, Cloneable{
   // data member: array for the list elements
   private E[] elements;
   // data member: size of the list
   private int size;
   /**
        Default constructor creates the array with a default length of 10 and sets size to 0
    */
   public ArrayList() {
	   elements = (E[]) new Object[10];
	   size = 0;
   }
   /**
        Constructor with one parameter creates the array with length equal to capacity and sets size to 0
        @param capacity length of the arrat elements
    */
   public ArrayList(int capacity) {
     elements = (E[]) new Object[capacity];
     size = 0;
   }
   /**
        Method to add a new item at the end of the list
        @param item the value of the item to be added
        @return true if item was added successfully, false otherwise
    */
    public boolean add(E item) {
		  add(size, item);
      return true;
    }
    /**
        Method to add a new item a given position index
        @param index the position where item should be added
        @param item the value of the element to be added
        @return true if item was added successfully, false otherwise
        @throws ArrayIndexOutOfBoundsException if index < 0 or index > size
    */
    public void add(int index, E item){
      if(index > size || index < 0)
        throw new ArrayIndexOutOfBoundsException();
      ensureCapacity();
      for(int i=size-1; i>=index; i--){
        elements[i+1] = elements[i];
      }
      elements[index] = item;
      size++;
    }

    /**
     * adds a new element at the beginning of this list
     * @param e the element to add
     * Time complexity: O(n), where n is the size of this list
     */
    public void addFirst(E e){
        add(0, e);
    }
    
    /**
     * adds a new element at the end of this list
     * @param e the element to add
     * Time complexity: O(1) most of the time, but O(n) if the array needs to grow
     */
    public void addLast(E e){
        add(size, e);
    }

    /**
        Get the value of the element at index
        @param index of the element being accessed
        @return the value of the element at index
        @throws ArrayIndexOutofBounds if index < 0 or index >= size
     */
    public E get(int index) {
		  checkIndex(index);
		  return elements[index];
    }

    /**
        Get the value of the element at the beginning of the list
        @return the value of the element at the beginning of the list
        @throws NoSuchElementException if the list is empty
        Time complexity: O(1)
     */
    public E getFirst() {
      if(size == 0) throw new NoSuchElementException("List empty");
      return elements[0];
    }

    /**
     * Get the value of the element at the end of the list
     * @return the value of the element at the end of the list
     * @throws NoSuchElementException if the list is empty
     * Time complexity: O(1)
     */
    public E getLast() {
      if(size == 0) throw new NoSuchElementException("List empty");
      return elements[size-1];
    }

    /**
        Set the value of the element at index
        @param index of the element being modified
        @param value new value of the element at index
        @return the old value of the element at index
        @throws ArrayIndexOutofBounds if index < 0 or index >= size
     */
    public E set(int index, E element) {
		  checkIndex(index);
		  E oldValue = elements[index];
		  elements[index] = element;
		  return oldValue;
    }
    /**
        Get the size of the list
        @return the number of elements in the list
     */
    public int size() {
      return size; 
    }
    /**
        Clear the list by setting size to 0
     */
    public void clear() {
      size = 0; 
    }
    /**
        Predicate to check if the list is empty
        @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
      return (size == 0);
    }
    /**
        Remove the element at a given index
        @param index the position of the element to be removed
        @return the value of the removed element
        @throws ArrayIndexOutofBoundsException if index < 0 or index >= size
     */
    public E remove(int index) {
      checkIndex(index);
      E value = elements[index];
      for(int i=index; i<size-1; i++){
			  elements[i] = elements[i+1];
      }
      size--;
      return value;
    }

    /**
     * Method to remove a given object
     * @param o the object to find and remove
     * @return true if o was found and removed, false if o was not found
     */
    public boolean remove(Object o){
      for(int i=0; i<size; i++){
        if(elements[i].equals(o)){
          remove(i);
          return true;
        }
      }
      return false;
    }

    /**
     * removes the element at the beginning of this list
     * @return the value of the removed element
     * @throws NoSuchElementException if the list is empty
     * Time complexity: O(n), where n is the size of this list
     */
    public E removeFirst(){
      if(size == 0) throw new NoSuchElementException("List empty");
      return remove(0);
    }
    /**
     * removes the element at the end of this list
     * @return the value of the removed element
     * @throws NoSuchElementException if the list is empty
     * Time complexity: O(1)
     */
    public E removeLast(){
      if(size == 0) throw new NoSuchElementException("List empty");
      return remove(size-1);
    }

    /**
     * Search method
     * @param o the object to lookup
     * @return true of o is found, false otherwise
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
     * Search method
     * @param o the object to find
     * @return the index of the first occurrence of o in the list if o was found, -1 otherwise
     */
    public int indexOf(Object o){
      for(int i=0; i<size; i++){
        if(elements[i].equals(o)){
          return i;
        }
      } 
      return -1;
    }
    /**
     * Search method
     * @param o the object to find
     * @return the index of the last occurrence of o in the list if o was found, -1 otherwise
     */
    public int lastIndexOf(Object o){
      for(int i=size-1; i>=0; i--){
        if(elements[i].equals(o)){
          return i;
        }
      }
      return -1;
    }

    /**
        @override iterator() from the interface Collection
        @return iterator object pointing to the first element the list
     */
    public Iterator<E> iterator(){
		  return new ArrayIterator();
    }

    /**
     * Get a list iterator for this list starting at the beginning of the list
     * @return the list iterator object
     * returns a list iteratoer from index 0
     */
    public ListIterator<E> listIterator(){
      return new ArrayListIterator(0);
    }
    /**
     * Get a list iterator for this list starting at the position index in the list
     * @return the list iterator object
     * listIterator returns the iterator from index
     */
    public ListIterator<E> listIterator(int index){
      return new ArrayListIterator(index);
    }

    /**
        Resize the length of the array 'elements' to the size of the list
     */
    public void trimToSize() {
		  if (size != elements.length) {
			    E[] newElements = (E[]) new Object[size];
			    for(int i=0; i<size; i++){
				    newElements[i] = elements[i];
          }elements = newElements;
		  }
    }
    /**
        @override toString() from class Object
        @return a formatted string containing the elements of the list
     */
    public String toString() {
      if(size == 0)
        return "[]";
	    String output = "[";
		  for(int i=0; i < size-1; i++){
		    output += elements[i] + " ";
      }output += elements[size-1] + "]";
		  return output;
    }
    

    /**
     * Overrides equals for type ArrayList
     * @param o the object being compared to this list
     * @return true if o and this list have the same size and the same elements in the same order, false otherwise
     */
    public boolean equals(Object o){
      if(!(o instanceof List)) 
        return false;
      List<?> other = (List<?>) o;
      if(this.size() != other.size()) 
        return false;
      for(int i=0; i<size; i++){
        if(!elements[i].equals(other.get(i))){
          return false;
        }
      }
      return true;
    }
  
    /**
     * Method to get the elements of this list in an array of objects
     * @return an array of type Object that contains the elements from this list
     */
    public Object[] toArray(){
      Object[] arr = new Object[size];
      for(int i=0; i<size; i++){
          arr[i] = elements[i];
      }
      return arr;
    }

    /**
     * Adds the elements of the collection c to this list
     * @param c the collection to be added to this list
     * @return true if the addition was successful
     * Time complexity: O(n), where n is the size of the collection c
     */
    public boolean addAll(Collection<? extends E> c){
      boolean changed = false;
      Iterator<? extends E> it = c.iterator();
      while(it.hasNext()){
          add(it.next()); // add at the end
          changed = true;
      }
      return changed;
    }

    /**
     * removes the elements of this list that are not in the collection c
     * @param c the collection to be compared to this list
     * @return true if the intersection was successful
     */
    public boolean retainAll(Collection<?> c){
      boolean changed = false;
      int i=0;
      while(i < size){
        if(!c.contains(elements[i])){
          remove(i);
          changed = true;
        } else {
          i++;
        }
      }
      return changed;
    }

    /**
     * removes the elements of this list that are in the collection c
     * @param c the collection to be compared to this list
     * @return true if the difference was successful
     */
    public boolean removeAll(Collection<?> c){
      boolean changed = false;
      int i=0;
      while(i < size){
        if(c.contains(elements[i])){
          remove(i);
          changed = true;
        } else {
          i++;
        }
      }
      return changed;
    }

    /**
     * Checks if the given collection is contained in this colection
     * @param c the collection to find in this collection
     */
    public boolean containsAll(Collection<?> c){
      Iterator<?> it = c.iterator();
      while(it.hasNext()){
        Object obj = it.next();
        if(!this.contains(obj)) return false;
      }
      return true;
    }

    /**
     * sorts the elements of this list using the natural ordering of E or a comparator
     * @param c the comparator to be used for sorting if c is not null
     * selection sort
     */
    public void sort(Comparator<? super E> c){
      if(size <= 1) return;
      for(int i=0; i<size-1; i++){
        int minPos = i;
        for(int j=i+1; j<size; j++){
          int cmp = 0;
          if(c == null){
            Comparable<E> cc = (Comparable<E>) elements[j];
            cmp = cc.compareTo(elements[minPos]);
          } else {
            cmp = c.compare(elements[j], elements[minPos]);
          }
          if(cmp < 0){
            minPos = j;
          }
        }
           // swap
        E temp = elements[i];
        elements[i] = elements[minPos];
        elements[minPos] = temp;
      }
    }
    /**
     * clone this list
     * @return a deep copy of this list
     */
    public Object clone(){
      try{
        ArrayList<E> copy = (ArrayList<E>) super.clone();
        copy.elements = (E[]) new Object[this.elements.length];
        for(int i=0; i<size; i++){
          copy.elements[i] = this.elements[i];
        }
      //copied since size is an int
        return copy;
      } catch(CloneNotSupportedException e){
        return null;
      }
    }

    /**
     * Method to return a reversed list of this list
     * @return a new list that contains this list reversed
     */
    public List<E> reversed(){
      ArrayList<E> rev = new ArrayList<>(size);
      for(int i=size-1; i>=0; i--){
        rev.add(elements[i]);
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
      if(from < 0 || to > size)
        throw new ArrayIndexOutOfBoundsException("Invalid");
      ArrayList<E> sub = new ArrayList<>();/// do not to-from for the capacity
                                           /// if from > to, the method returns an empty list
      for(int i=from; i<to; i++){
        sub.add(elements[i]);
      }
      return sub;
    }

    /*******************************************************
     * Inner class to implement the interface Iterator<E>  *
     *******************************************************/
    private class ArrayIterator implements Iterator<E>{
      // data member current
      // the index of the element at which the iterator is pointing
	    private int current = 0;

      /**
        @return true if current did not reach the end of the list, false otherwise
       */
	    public boolean hasNext() {
        return current < size; 
      }
      /**
        @return the value of the current element and moves the index current to the next element
        @throws ArrayIndexOutOfBoundsException if current is out of bounds
      */
	    public E next() {
        if(current < 0 || current >= size)
          throw new NoSuchElementException();
        return elements[current++]; 
      }
    } 
    
    /*******************************************************************************
     * Inner class to implement the interface ListIterator for the class ArrayList *
     *******************************************************************************/
    private class ArrayListIterator implements ListIterator<E>{
        private int current;
        /**
         * Default constructor
         */
        public ArrayListIterator(){
          current = 0;
        }
        /**
         * Constructor to start at index
         * @param index the position where the iterator starts
         */
        public ArrayListIterator(int index){
          //check index, if not throw exception
          current = index; //position
        }
        /**
         * checks if the iterator has a next element in the list
         * @return true if the end of the list is not reached
         */
        public boolean hasNext() {
          //check if it is less than size (return true)
            return (current<size);
        }
        /**
         * get the next element
         * @return the value of the next element pointed to by the iterator
         * @throws NoSuchElementException if no next element can be accessed
         */
        public E next() {
          //save value of elements of current and push current++ and re
          //follow previous class
          if(!hasNext()) throw new NoSuchElementException();
          return elements[current++];
        }
        /**
         * checks if the iterator has a previous element in the list
         * @return true if the beginning of the list is not reached
         */
        public boolean hasPrevious(){
          //check if current > 0
            return (current>0);
        }
         /**
         * get the previous element
         * @return the value of the element element pointed to by the iterator
         * @throws NoSuchElementException if no previous element can be accessed
         */
        public E previous() {
          //return elements of -- current
          if(!hasPrevious()) throw new NoSuchElementException();
          return elements[--current];
        }
        public void add(E e){
            throw new UnsupportedOperationException();
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
        public int nextIndex(){
          return current;
        }
        public int previousIndex(){
          return current - 1;
        }
        public void set(E e){
            throw new UnsupportedOperationException();
        }
    }
    /**
        Grow the length of the array 'elements' by 1.5 if it is full
    */
    private void ensureCapacity() {
	    if(size >= elements.length) {
          int newCap = (int) (elements.length * 1.5);
		      E[] newElements = (E[]) new Object[newCap];
		      for(int i=0; i<size; i++){
				    newElements[i] = elements[i];
          }
		      elements = newElements;
	    }
    }
    /**
        Check if the index is valid
        @param index to be checked
        @throws ArrayIndexOutOFBoundsException is index is out of bounds
     */
    private void checkIndex(int index){
		  if(index < 0 || index >= size)
			    throw new ArrayIndexOutOfBoundsException(
              "Index out of bounds. Must be between 0 and "+(size-1));
    }
}
