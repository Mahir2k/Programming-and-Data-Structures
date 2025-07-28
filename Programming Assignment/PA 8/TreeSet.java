import java.util.Comparator;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class to implement a binary search tree data structure using linked nodes
 */
public class TreeSet<E> implements Cloneable{
    // data members
    private TreeNode root;
    private int size;
    private Comparator<E> comparator;
    // Inner class for the tree nodes
    private class TreeNode{
        E value;
        TreeNode left, right;
        TreeNode(E val){
            value = val;
            left = right = null;
        }
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
        if(comparator == null){
            return ((Comparable) value1).compareTo(value2);
        }
        else{
            return comparator.compare(value1, value2);
        }
    }
    /**
     * Default constructor
     * Create an empty binary search tree
     * O(1)
     */
    public TreeSet(){
        root = null;
        size = 0;
        comparator = null;
    }
    /**
     * Constructor with a comparator
     * Create an empty binary search tree
     * O(1)
     */
    public TreeSet(Comparator<E> c){
        root = null;
        size = 0;
        comparator = c;
    }
    /**
     * Getter of the size of the tree
     * @return the number of nodes in the tree
     * O(1)
     */
    public int size(){
        return size;
    }
    /**
     * @return true if the tree is empty, false otherwise
     * O(1)
     */
    public boolean isEmpty(){
        return (size == 0);
    }
    /**
     * clears the tree
     * O(1)
     */
    public void clear(){
        root = null;
        size = 0;
    }
    /**
     * Search method
     * @param element the value being looked up
     * @return true if element is found in the tree, false otherwise
     * O(log n)
     */
    public boolean contains(E element){
        if (root == null){
            return false;
        }
        TreeNode current = root;
        while(current != null){
            int c = compare(element,current.value);
            if(c == 0)
                return true;
            else if(c < 0)
                current = current.left;
            else
                current = current.right;
        }
        return false;   
    }
    /**
     * Ading a new element to the tree
     * @param element the value to add in the tree
     * @return true if element was added succesfully, false if element already exists in the tree
     * O(log n)
     */
    public boolean add(E element){
        TreeNode newNode = new TreeNode(element);
        if (root == null){
            root = newNode;
        }
        else{
            TreeNode current = root, parent = null;
            while(current != null){
                parent = current;
                int c = compare(element, current.value);
                if(c == 0)
                    return false;
                else if(c < 0)
                    current = current.left;
                else
                    current = current.right;
            }
            if(compare(element,parent.value) < 0)
                parent.left = newNode;
            else
                parent.right = newNode;
        }
        size++;
        return true;
    }
    /**
     * Removing an element from the tree
     * @param element the value to be removed from the tree
     * @return true if element was found and removed, false if element was not found
     * O(log n)
     */
    public boolean remove(E element){
        if (root == null){
            return false;
        }
        // First, find the node with value element
        TreeNode node = root, parent = null;
        while(node != null){
            int c = compare(element, node.value);
            if(c == 0){
                break;
            }
            parent = node;
            if(c < 0)
                node = node.left;
            else
                node = node.right;
        }
        if (node == null){
            return false; // value not found
        }
        // case 1: Leaf node
        if(node.left == null && node.right == null){
            if(parent == null){ // the only node in the tree
                root = null; size = 0;
            }
            else{
                if(parent.left == node)
                    parent.left = null;
                else
                    parent.right = null;
            }
        }
        // case 2: node has a right child only
        else if(node.left == null){
            if(parent == null)
                root = node.right;
            else if (parent.left == node)
                parent.left = node.right;
            else   
                parent.right = node.right;
        }
        // case 2: node has a left child only
        else if(node.right == null){
            if(parent == null)
                root = node.left;
            else if(parent.left == node)
                parent.left = node.left;
            else
                parent.right = node.left;
        }
        // case 3: node has two children
        else{
            TreeNode rightMostCurrent = node.left;
            TreeNode rightMostParent = node;
            while(rightMostCurrent.right != null){
                rightMostParent = rightMostCurrent;
                rightMostCurrent = rightMostCurrent.right;
            }
            node.value = rightMostCurrent.value;
            if(rightMostParent.left == rightMostCurrent)
                rightMostParent.left = rightMostCurrent.left;
            else
                rightMostParent.right = rightMostCurrent.left;
        }
        size--;
        return true;
    }
   
    /**
     * Preorder Traversal method
     * O(n)
     */
    public void preorder(){
        System.out.print("[");
        preorder(root);
        System.out.println("]");
    }
    /**
     * Preorder Traversal recursive helper method
     * @param node the node where the traversal starts
     * O(n)
     */
    public void preorder(TreeNode node){
        if(node != null){
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }
    /**
     * Inorder Traversal method
     * O(n)
     */
    public void inorder(){
        System.out.print("[");
        inorder(root);
        System.out.println("]");
    }
    /**
     * Inorder Traversal recursive helper method
     * @param node the node where the traversal starts
     * O(n)
     */
    public void inorder(TreeNode node){
        if(node != null){
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }
    /**
     * Postorder Traversal method
     * O(n)
     */
    public void postorder(){
        System.out.print("[");
        postorder(root);
        System.out.println("]");
    }
    /**
     * Postorder Traversal recursive helper method
     * @param node the node where the traversal starts
     * O(n)
     */
    public void postorder(TreeNode node){
        if(node != null){
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }  
    /**
     * Adds the elements of the collection c to this tree
     * @param c the collection to be added to this tree
     * @return true if the addition was successful
     * O(1)
     */
    public boolean addAll(Collection<? extends E> c){
        boolean changed=false;
        for(E e:c) 
            if(add(e)) 
            changed=true;
        return changed;
    }
    /**
     * removes the elements of this tree that are not in the collection c
     * @param c the collection to be compared to this tree
     * @return true if the intersection was successful
     * O(n log n)
     */
    public boolean retainAll(Collection<?> c){
        boolean changed=false;
        Iterator<E> it=iterator();
        ArrayList<E> toRemove=new ArrayList<>();
        while(it.hasNext()){
            E val=it.next();
            if(!c.contains(val)) toRemove.add(val);
        }
        for(E val:toRemove){ 
            remove(val); 
            changed=true; 
        }
        return changed;
    }
    /**
     * removes the elements of this tree that are in the collection c
     * @param c the collection to be compared to this tree
     * @return true if the difference was successful
     * O(1)
     */
    public boolean removeAll(Collection<?> c){
        boolean changed=false;
        for(Object o:c) 
            if(remove((E)o)) 
            changed=true;
        return changed;
    }

    /**
     * first method
     * @return the lowest value in the tree
     * O(log n)
     */
    public E first(){
        if(root==null) return null;
        TreeNode n=root;
        while(n.left!=null) n=n.left;
        return n.value;
    }
    /**
     * last method
     * @return the highest value in the tree
     * O(log n)
     */
    public E last(){
        if(root==null) return null;
        TreeNode n=root;
        while(n.right!=null) n=n.right;
        return n.value;
    }
    /**
     * ceiling method
     * @return the least element in this tree greater than or equal to the given value, or
     *         null if there is no such element
     * O(log n)
     */
    public E ceiling(E value){
        TreeNode current=root;
        E candidate=null;
        while(current!=null){
            int c = compare(value, current.value);
            if(c==0)
                return current.value;
            if(c<0){ 
                candidate=current.value; 
                current=current.left; 
            }
            else
                current=current.right;
        }
        return candidate;
    }
    /**
     * floor method
     * @return the greatest element in this tree less than or equal to the given value, or 
     *         null if there is no such element
     * O(log n)
     */
    public E floor(E value){
        TreeNode current=root;
        E candidate=null;
        while(current!=null){
            int c = compare(value, current.value);
            if(c==0)
                return current.value;
            if(c>0){ 
                candidate=current.value; 
                current=current.right; 
            }
            else
                current=current.left;
        }
        return candidate;

    }
    /**
     *   iterator() method
     *   @return iterator object pointing to the first element in the inorder traversal
     * O(n)
     */
    public Iterator<E> iterator(){
        return new TreeSetIterator();
    }
    /*******************************************************
     * Inner class to implement the interface Iterator<E>  *
     * (inorder traversal implemented by the iterator)     *
     *******************************************************/
    private class TreeSetIterator implements Iterator<E>{
        private int current; 
        ArrayList<E> nodes = new ArrayList<>();
        /**
         * Default constructor
         */
        public TreeSetIterator(){
            inorder(root);
            current = 0;
        }
        private void inorder(TreeNode node){
            if(node != null){
                inorder(node.left);
                nodes.add(node.value);
                inorder(node.right);
            }
        }
        /**
         *    @return true if there is a next element to access in the tree, false otherwise
         * O(1)
         */
        public boolean hasNext(){
            return(current<nodes.size());
        }
        /**
         * @return the value of the current element and moves to the next element
         * @throws ArrayIndexOutOfBoundsException if there is no current element
         * O(1)
         */
        public E next(){
            if(current>=nodes.size())
                throw new NoSuchElementException();
            return nodes.get(current++);
        }
    }
    /**
     * clone this tree
     * @return a deep copy of this tree
     * O(n log n)
     */
    public Object clone(){
        TreeSet<E> copy= new TreeSet<E>(comparator);
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            copy.add(iter.next());
        }
        return copy;
    }

}