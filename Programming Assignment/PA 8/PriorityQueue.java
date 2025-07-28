import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;

public class PriorityQueue<E> implements Cloneable{
    // ArrayList where the nodes of the heap are stored
    private ArrayList<E> list;
    private Comparator<E> comp;
    /**
     * Default Constructor
     */
    public PriorityQueue(){
        list = new ArrayList<>();
        comp = null;
    }
    /**
     * Constructor with one arg
     * @param c the comparator object used to order the nodes of the heap
     * Time complexity: O(1)
     */
    public PriorityQueue(Comparator<E> c){
        list = new ArrayList<>();
        comp = c;
    }
    /**
     * private method to compare two E values using natural ordering or the comparator object
     * @param value1 the first value
     * @param value2 the second value
     * @return 0   if value1 and value2 are equal
     *         > 0 if value1 is after value2
     *         < 0 if value1 is before value2
     */
    private int compare(E value1, E value2){
        if(comp == null)
            return ((Comparable)value1).compareTo(value2);
        else
            return comp.compare(value1, value2);
    }
    /**
     * Method size
     * @return the number of nodes in the heap
     * Time complexity: O(1)
     */
    public int size(){
        return list.size(); 
    }
    /**
     * Method isEmpty
     * @return true if the heap is empty, false otherwise
     * Time complexity: O(1)
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }
    /**
     * Method to empty the heap
     * Time complexity: O(1)
     */
    public void clear(){
        list.clear(); 
    }
    /**
     * Method toString
     * @return a formatted string containing the values of the nodes of the heap
     * Time complexity: O(n)
     */
    public String toString(){
        return list.toString();
    }
    /**
     * Method to access the value of the root
     * @return the value of the root
     * Time complexity: O(1)
     */
    public E peek(){
        if (list.isEmpty()){
            throw new NoSuchElementException();
        }
        return list.get(0);
    }
    /**
     * Method offer
     * @param value to be added to the heap
     * reconstructs the heap to keep the MinHeap properties
     * Time complexity: O(logn)
     */
    public void offer(E value) {
        list.add(value);
        int currentIndex = list.size()-1; 
        checkHeap(currentIndex, true);
    }
    /**
     * Method pop
     * @return the value of the root, null if the heap is empty
     * reconstructs the heap to keep the MinHeap properties
     * Time complexity: O(logn)
     */
    public E poll() {
        if(list.isEmpty()) 
            return null;
        E removedItem = list.get(0);
        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);
        int currentIndex = 0;
        checkHeap(currentIndex, false);
        return removedItem;
    }
    /**
     * checkHeap method
     * @param nodeIndex node where the checking should start
     * @param upDown true if checking from nodeIndex up to the root
     *               false if checking from nodeIndex down to the end of the heap 
     * O(logn)
     */
    private void checkHeap(int nodeIndex, boolean upDown) {
        if(upDown){
            if(nodeIndex == 0)
                return;                       
            int parent = (nodeIndex-1)/2;
            if(compare(list.get(nodeIndex), list.get(parent))<0){
                E temp = list.get(nodeIndex);
                list.set(nodeIndex, list.get(parent));
                list.set(parent, temp);
                nodeIndex = parent;
                checkHeap(parent, upDown);
            }
        }else{     
            if(nodeIndex >= list.size())
            return;                         
            int left = 2*nodeIndex+1, right = left+1, smallest = nodeIndex;
            if(left<list.size() && compare(list.get(left), list.get(smallest))<0)
                    smallest = left;
            if(right<list.size() && compare(list.get(right), list.get(smallest))<0)
                    smallest = right;
            if(compare(list.get(smallest), list.get(nodeIndex))<0){
                E temp = list.get(nodeIndex);
                list.set(nodeIndex, list.get(smallest));
                list.set(smallest, temp);
                nodeIndex = smallest;
                checkHeap(smallest, upDown);
            }
        }
    }
    /**
     * Search method
     * @param o the object to lookup
     * @return true of o is found, false otherwise
     * O(n)
     */
    public boolean contains(Object o){
        for(E e:list) 
            if(e.equals(o)) 
                return true;
        return false;
    }
    /**
        iterator() method
        @return iterator object pointing to the first element in the list
        O(n)
     */
    public Iterator<E> iterator(){
        return new PriorityQueueIterator();
    }
   /*******************************************************
    * Inner class to implement the interface Iterator<E>  *
    * (ascending order implemented by the iterator)       *
    *******************************************************/
    private class PriorityQueueIterator implements Iterator<E>{
        private PriorityQueue<E> copy;
        /**
         * Default constructor
         */
        public PriorityQueueIterator(){ 
            copy = (PriorityQueue)PriorityQueue.this.clone();
        }
        /**
         *  @return true if current did not reach the end of the list, false otherwise
         * O(1)
         */
        public boolean hasNext(){
            return !copy.isEmpty();
        }
        /**
         * @return the value of the current element and moves to the next element
         * @throws ArrayIndexOutOfBoundsException if no element can be accessed
         * O(logn)
         */
        public E next(){
            if(!hasNext()) 
                throw new NoSuchElementException();
            return copy.poll();
        }

    }
    /**
     * Method to remove a given object
     * @param o the object to find and remove
     * @return true if o was found and removed, false if o was not found
     * O(n)
     */
    public boolean remove(Object o){
        int nodeIndex=-1;
        for(int i=0;i<list.size();i++)
            if(list.get(i).equals(o)){ 
                nodeIndex=i; break; 
            }
        if(nodeIndex==-1) 
            return false;

        list.set(nodeIndex,list.get(list.size()-1));
        list.remove(list.size()-1);
        if(nodeIndex<list.size()){
            checkHeap(nodeIndex,false);
            checkHeap(nodeIndex,true);
        }
        return true;
    }
    /**
     * clone this heap
     * @return a deep copy of this heap
     * O(n logn)
     */
    public Object clone(){
            PriorityQueue<E> copy=new PriorityQueue<E>(comp);
            for(E element: list){
                copy.offer(element);
            }
            return copy;
    }
    /**
     * Adds the elements of the collection c to this list
     * @param c the collection to be added to this list
     * @return true if the addition was successful
     * Time complexity: O(n), where n is the size of the collection c
     */
    public boolean addAll(Collection<?> c){
        boolean changed=false;
        for(Object obj:c){
            offer((E)obj);
            changed=true;
        }
        return changed;
    }
    /**
     * removes the elements of this heap that are not in the collection c
     * @param c the collection to be compared to this heap
     * @return true if the intersection was successful
     * O(n logn)
     */
    public boolean retainAll(Collection<?> c){
        boolean changed=false;
        Iterator<E> it=list.iterator();
        while(it.hasNext()){
            if(!c.contains(it.next())){
                it.remove();
                changed=true;
            }
        }
        if(changed && !list.isEmpty()) 
            for(int i=(list.size()/2)-1;i>=0;i--) 
                checkHeap(i,false);
        return changed;
    }
    /**
     * removes the elements of this heap that are in the collection c
     * @param c the collection to be compared to this heap
     * @return true if the difference was successful
     * O(n logn)
     */
    public boolean removeAll(Collection<?> c){
        boolean changed=false;
        Iterator<E> it=list.iterator();
        while(it.hasNext()){
            if(c.contains(it.next())){
                it.remove();
                changed=true;
            }
        }
        if(changed && !list.isEmpty())
            for(int i=(list.size()/2)-1;i>=0;i--) 
                checkHeap(i,false);
        return changed;
    }
}
