import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.ArrayList;
/**
 * Class Priority Queue that implements a min heap
 */
public class PriorityQueue<E> {
    public static int offerIter, pollIter; 
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
     */
    public PriorityQueue(Comparator<E> c){
        list = new ArrayList<>();
        comp = c;
    }
    /**
     * Method size
     * @return the number of nodes in the heap
     */
    public int size(){
        return list.size(); 
    }
    /**
     * Method isEmpty
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }
    /**
     * Method to empty the heap
     */
    public void clear(){
        list.clear(); 
    }
    /**
     * Method toString
     * @return a formatted string containing the values of the nodes of the heap
     */
    public String toString(){
        return list.toString();
    }
    /**
     * Method getRoot
     * @return the value of the root
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
     */
    public void offer(E value) {
        offerIter = 0;
        list.add(value);
        int currentIndex = list.size()-1; 
        while(currentIndex > 0) {
            offerIter++;
            int parentIndex = (currentIndex-1)/2;
            E current = list.get(currentIndex);
            E parent = list.get(parentIndex);
            int comparison = (comp == null)? ((Comparable)(current)).compareTo(parent): comp.compare(current, parent);
            if(comparison < 0) {
                list.set(currentIndex, parent);
                list.set(parentIndex, current);
            }
            else
                break;
            currentIndex = parentIndex;
        }
    }
    /**
     * Method poll
     * @return the value of the root, null if the heap is empty
     * reconstructs the heap to keep the MinHeap properties
     */
    public E poll() {
        pollIter = 0;
        if(list.isEmpty()) 
            return null;
        E removedItem = list.get(0);
        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);
        int currentIndex = 0;
        while (currentIndex < list.size()) {
            pollIter++;
            int left = 2 * currentIndex + 1;
            int right = 2 * currentIndex + 2;
            if (left >= list.size()) 
                break;
            int minIndex = left;
            E min = list.get(minIndex);
            if (right < list.size()){
                int comparison = (comp == null)? ((Comparable)(min)).compareTo(list.get(right)): comp.compare(min, list.get(right));
                if(comparison > 0)
                    minIndex = right;
            }
            E current = list.get(currentIndex);
            min = list.get(minIndex); 
            int comparison = (comp == null)? ((Comparable)(current)).compareTo(min): comp.compare(current, min);           
            if(comparison > 0){
                list.set(minIndex, current);
                list.set(currentIndex, min);
                currentIndex = minIndex;
            }
            else
                break;
        }
        return removedItem;
    }

    /**
     * Helper method for heigh
     * @return the height method at index 0
     * Time complexity: O(n) helper method has the same one as the main method
     */
    public int height(){
        return height(0); //start the recursion from the root of the heap
    }

    /**
     * A recursive method to return the height of the binary tree for the heap
     * @return the height of the binary tree representing the heap
     * Time complexity: O(n) visit every node in the heap
     */
    private int height(int nodeIndex){
        if(nodeIndex >= list.size()){ //node is null: empty tree
            return 0;
        }
        int hL = height(nodeIndex * 2 + 1); //height of the left subtree
        int hR = height(nodeIndex * 2 + 2);
        return 1 + Math.max(hL, hR);
    }

    /**
     * Helper method for the isBalanced method
     * @return the 0 index of the isBalanced method
     * Time complexity: O(n^2)
     */
    public boolean isBalanced(){
        return isBalanced(0);
    }

    /**
     * A recursive method that tests if the heap is balanced
     * @return true if heap is balanced and false if not
     * Time complexity: O(n^2) (calling the height of every node so O(n * n))
     */
    private boolean isBalanced(int nodeIndex){
        //base case one: empty tree
        if(nodeIndex >= list.size()){
            return true;
        }
        int hL = height(nodeIndex * 2 + 1);
        int hR = height(nodeIndex * 2 + 2);
        //base case 2: balance property is not verified at this node
        if(Math.abs(hL - hR) > 1){
            return false;
        }
        //first one checks the left subtree
        //second one checks the right subtree
        return isBalanced(nodeIndex * 2 + 1) && isBalanced(nodeIndex * 2 + 2);
    }
}