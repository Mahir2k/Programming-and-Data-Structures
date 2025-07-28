import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * HashMapSC implements a hash table with separate chaining
 */
public class HashMapSC<K, V> extends HashMap<K, V> {
    private int size;
    private LinkedList<MapEntry<K, V>>[] hashTable;

    /** Default constructor creates the hash table with capacity 100 and load factor 0.9 */
    public static int iterations;
    public static int collisions;

    public HashMapSC() {
        this(100, 0.9);
    }
    public HashMapSC(int c) {
        this(c, 0.9);
    }
    public HashMapSC(int c, double lf) {
        hashTable = new LinkedList[trimToPowerOf2(c)];
        loadFactor = lf;
        size = 0;
        iterations = 0;
        collisions = 0;
    }

    /**
     * Method hash
     * @param hashCode
     * @return a valid index in the hashtable
     */
    protected int hash(int hashCode) {
        return hashCode & (hashTable.length - 1);
    }

    /**
     * Method rehash
     * creates a new hashtable with double capacity
     * puts all the entries from the old hashtable into the new table
     */
    protected void rehash() {
        ArrayList<MapEntry<K, V>> list = toList();
        hashTable = new LinkedList[hashTable.length << 1];
        size = 0;
        collisions = 0;
        for (MapEntry<K, V> entry : list) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Method size
     * @return the number of pairs (key,value) stored in the hashtable
     */
    public int size() {
        return size;
    }

    /**
     * Method clear to clear the hashtable
     */
    public void clear() {
        size = 0;
        iterations = 0;
        collisions = 0;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                hashTable[i].clear();
            }
        }
    }

    /**
     * Method isEmpty
     * @return true if the hashtable is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method get to find an entry in the hashtable
     * @param key the value of the key being searched for
     * @return the value associated with the key if key is found, null otherwise
     */
    public V get(K key) {
        iterations = 0;
        int HTIndex = hash(key.hashCode());
        LinkedList<MapEntry<K, V>> ll = hashTable[HTIndex];
        if (ll != null) {
            Iterator<MapEntry<K, V>> iter = ll.iterator();
            while (iter.hasNext()) {
                iterations++;
                MapEntry<K, V> entry = iter.next();
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Method put to add a new entry to the hashtable
     * @param key the value of the key of the new entry
     * @param value the value associated with the key
     * @return the old value of the entry if an entry is found for key
     *         or the new value if a new entry was added to the hashtable
     */
    public V put(K key, V value) {

        // key already present-  update
        if (get(key)!= null) {
            int HTIndex = hash(key.hashCode());
            LinkedList<MapEntry<K, V>> ll = hashTable[HTIndex];
            Iterator<MapEntry<K, V>> iter = ll.iterator();
            while (iter.hasNext()) {
                MapEntry<K, V> entry = iter.next();
                if (entry.getKey().equals(key)) {
                    V old = entry.getValue();
                    entry.setValue(value);
                    return old;
                }
            }
        }

        // key not present - maybe grow
        if (size >= hashTable.length * loadFactor) {
            rehash();
        }
        int HTIndex = hash(key.hashCode());
        // insert new
        if (hashTable[HTIndex] == null) {
            hashTable[HTIndex] = new LinkedList<>();
        } else {
            collisions++;
        }
        hashTable[HTIndex].add(new MapEntry<>(key, value));
        size++;
        return value;
    }

    /**
     * Method toList
     * @return an arraylist with all the entries in the hashtable
     */
    public ArrayList<MapEntry<K, V>> toList() {
        ArrayList<MapEntry<K, V>> list = new ArrayList<>();
        for (LinkedList<MapEntry<K, V>> bucket : hashTable) {
            if (bucket != null) {
                for (MapEntry<K, V> entry : bucket) {
                    list.add(entry);
                }
            }
        }
        return list;
    }

    /**
     * Method toString
     * @return a formatted string with all the entries in the hashtable
     */
    public String toString() {
        // not used by tests, so keep it simple
        return toList().toString();
    }

    /**
     * Method getCharacteristics
     * @return an array of six integers:
     *         index  0: capacity of the hash table
     *         index  1: size of the hash table
     *         index  2: total collisions counted during puts
     *         index  3: number of non-empty buckets (clusters)
     *         index  4: size of the largest bucket
     *         index  5: size of the smallest bucket
     */
    public int[] getCharacteristics() {
        int[] stats = new int[6];
        stats[0] = hashTable.length;
        stats[1] = size;
        stats[2] = collisions;

        int buckets = 0;
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (LinkedList<MapEntry<K, V>> bucket : hashTable) {
            if (bucket != null && !bucket.isEmpty()) {
                buckets++;
                int bsize = bucket.size();
                if (bsize > max) max = bsize;
                if (bsize < min) min = bsize;
            }
        }
        if (buckets == 0) min = 0;

        stats[3] = buckets;
        stats[4] = max;
        stats[5] = min;
        return stats;
    }
}