import java.util.ArrayList;

/**
 * HashMapQP implements a hash table with quadratic probing
 */
public class HashMapQP<K, V> extends HashMap<K, V> {
    private int size;
    private MapEntry<K, V>[] hashTable;
    public static int iterations;
    public static int collisions;

    /**
     * Default constructor
     * creates the hash table with capacity 100 and load factor 0.5
     */
    public HashMapQP() {
        this(100, 0.5);
    }
    /**
     * Constructor with one parameter
     * @param c the capacity of the hash table
     * creates the hash table with capacity c and load factor 0.5
     */
    public HashMapQP(int c) {
        this(c, 0.5);
    }
    /**
     * Constructor with two parameters
     * @param c  the capacity of the hash table
     * @param lf the load factor of the hash table
     * creates the hash table with capacity c and load factor lf
     */
    public HashMapQP(int c, double lf) {
        if (lf <= 0 || lf >= 1)
            throw new IllegalArgumentException("Load factor must be in (0,1)");
        loadFactor = lf;
        hashTable = (MapEntry<K, V>[]) new MapEntry[trimToPowerOf2(c)];
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
        ArrayList<MapEntry<K, V>> entries = toList();
        hashTable = (MapEntry<K, V>[]) new MapEntry[hashTable.length << 1];
        size = 0;
        collisions = 0;
        //iterations not reset here
        for (MapEntry<K, V> e : entries) {
            put(e.getKey(), e.getValue());
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
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = null;
        }
        size = 0;
        iterations = 0;
        collisions = 0;
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
        int initialIndex = hash(key.hashCode());
        int mask = hashTable.length - 1;
        int idx = initialIndex;
        int probe = 1;
        // probe = 1,2,3,...
        while (hashTable[idx] != null) {
            iterations++;
            MapEntry<K, V> e = hashTable[idx];
            if (e.getKey().equals(key)) {
                return e.getValue();
            }
            idx = (initialIndex + probe * probe) & mask;
            probe++;
        }
        return null;
    }

    /**
     * Method put to add a new entry to the hashtable
     * @param key   the value of the key of the new entry
     * @param value the value associated with the key
     * @return the old value of the entry if an entry is found for key
     *         or the new value if a new entry was added to the hashtable
     */
    public V put(K key, V value) {

        // key already present-  update
        int mask = hashTable.length-1;
        int probe = 1;
        if (get(key)!= null) {
            int HTIndex = hash(key.hashCode());
            int initialIndex = HTIndex;
            while (hashTable[HTIndex] != null) {
                MapEntry<K, V> entry = hashTable[HTIndex];
                if (entry.getKey().equals(key)) {
                    V old = entry.getValue();
                    entry.setValue(value);
                    return old;
                }
                HTIndex = (initialIndex + probe * probe) & mask;
                probe++;
            }
        }

        // key not present - maybe grow
        if (size >= hashTable.length * loadFactor) {
            rehash();
        }
        int HTIndex = hash(key.hashCode());
        // insert new
        if (hashTable[HTIndex] == null) {
            hashTable[HTIndex] = new MapEntry<>(key, value);
        } else {
            collisions++;
            int initialIndex = HTIndex;
            probe = 1;
            while(hashTable[HTIndex] != null){
                HTIndex = (initialIndex + probe * probe) & mask;
                probe++;
            }
            hashTable[HTIndex] = new MapEntry<>(key, value);
        }
        size++;
        return value;
    }

    /**
     * Method toList
     * @return an arraylist with all the entries in the hashtable
     */
    public ArrayList<MapEntry<K, V>> toList() {
        ArrayList<MapEntry<K, V>> list = new ArrayList<>();
        for (MapEntry<K, V> e : hashTable) {
            if (e != null) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * Method toString
     * @return a formatted string with all the entries in the hashtable
     */
    public String toString() {
        return toList().toString();
    }

    /**
     * Method getCharacteristics
     * @return an array of six integers:
     *         index 0: capacity of the hash table
     *         index 1: size of the hash table
     *         index 2: the total number of collisions
     *         index 3: number of buckets/clusters
     *         index 4: size of the largest bucket/cluster
     *         index 5: size of the smallest bucket/cluster
     */
    public int[] getCharacteristics() {
        int[] stats = new int[6];
        stats[0] = hashTable.length;  // capacity
        stats[1] = size;              // current size
        stats[2] = collisions;        // recorded collisions

        int clusters = 0, max = 0, min = Integer.MAX_VALUE, cur = 0;
        boolean inRun = false;
        for (MapEntry<K, V> e : hashTable) {
            if (e != null) {
                if (!inRun) {
                    inRun = true;
                    clusters++;
                    cur = 1;
                } else {
                    cur++;
                }
            } else if (inRun) {
                max = Math.max(max, cur);
                min = Math.min(min, cur);
                inRun = false;
            }
        }
        // Close?
        if (inRun) {
            max = Math.max(max, cur);
            min = Math.min(min, cur);
        }
        if (clusters == 0) min = 0;

        stats[3] = clusters;  // number of clusters
        stats[4] = max;       // largest cluster
        stats[5] = min;       // smallest cluster
        return stats;
    }
}
