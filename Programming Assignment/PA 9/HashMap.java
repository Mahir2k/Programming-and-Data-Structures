import java.util.ArrayList;
import java.util.Iterator;
/**
 * Abstract class HashMap
 */
public abstract class HashMap<K,V>{
	protected double loadFactor;
	/**
    * Method trimToPowerOf2
    * @param c the capacity of the hashtable
    * @return the closest power of 2 to c
    */
	protected int trimToPowerOf2(int c) {
		int capacity = 1;
		while (capacity < c)
			capacity  = capacity << 1; // * 2
		return capacity;
	}

	/**
     * Method hash
     * @param hashCode
     * @return a valid index in the hashtable
     */
	protected abstract int hash(int hashCode);
	/**
     * Method rehash
     * creates a new hashtable with double capacity
     * puts all the entries from the old hashtable into the new table
     */
	protected abstract void rehash();
	/**
     * Method size
     * @return the number of pairs (key,value) stored in the hashtable
     */
	public abstract int size();
	/**
     * Method clear to clear the hashtable
     */
	public abstract void clear();
	/**
     * Method isEmpty
     * @return true if the hashtable is empty, false otherwise
     */
	public abstract boolean isEmpty();
	/**
     * Method contains to search for a key in the hashtable
     * @param key the value of the key being searched for
     * @return true if key was found, false otherwise
     */
	public boolean containsKey(K key) {
		if(get(key) != null)
			return true;
		return false;
	}
	/**
     * Method get to find an entry in the hashtable
     * @param key the value of the key being searched for
     * @return the value associated with the key if key is found, null otherwise
     */
	public abstract V get(K key);
	
	/**
     * Method put to add a new entry to the hashtable
     * @param key the value of the key of the new entry
     * @param value the value associated with the key
     * @return the old value of the entry if an entry is found for key
     *         or the new value if a new entry was added to the hashtable
     */
	public abstract V put(K key, V value);
	/**
     * Method toList
     * @return an arraylist with all the entries in the hashtable
     */
	public abstract ArrayList<MapEntry<K,V>> toList();
	/**
     * Method toString
     * @return a formatted string with all the entries in the hashtable
     */
	public abstract String toString();
	/**
	 * Method getCharacterirstics
	 * @return an array of six integers:
	 *         index 0: capacity of the hash table
	 *         index 1: size of the hash table
	 *         index 2: the maximum number of collisions
	 *         index 3: the number of buckets/clusters
	 *         index 4: the size of the largest bucket/cluster
	 *         index 5: the size of the smallest bucket/cluster
	 */
	public abstract int[] getCharacteristics();

}