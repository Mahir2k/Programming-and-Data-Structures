import java.util.ArrayList;
import java.util.Iterator;

/**
 * HashMapLP implements a hash table with quadratic probing
 */
public class HashMapLP<K, V> extends HashMap<K, V> {
    private int size;
    private MapEntry<K, V>[] hashTable;
    public static int iterations;
    public static int collisions;

    /** Default constructor: capacity=100, loadFactor=0.5 */
    public HashMapLP() {
        this(100, 0.5);
    }
    public HashMapLP(int c) {
        this(c, 0.5);
    }
    public HashMapLP(int c, double lf) {
        if (lf <= 0 || lf >= 1)
            throw new IllegalArgumentException("Load factor must be in (0,1)");
        loadFactor = lf;
        hashTable = (MapEntry<K, V>[]) new MapEntry[trimToPowerOf2(c)];
        size = 0;
        iterations = 0;
        collisions = 0;
    }


    protected int hash(int hashCode) {
        return hashCode & (hashTable.length - 1);
    }


    protected void rehash() {
        ArrayList<MapEntry<K, V>> entries = toList();
        hashTable = (MapEntry<K, V>[]) new MapEntry[hashTable.length << 1];
        size = 0;
        collisions = 0;
        // don't reset iterations??
        for (MapEntry<K, V> e : entries) {
            put(e.getKey(), e.getValue());
        }
    }


    public int size() {
        return size;
    }


    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = null;
        }
        size = 0;
        iterations = 0;
        collisions = 0;
    }


    public boolean isEmpty() {
        return size == 0;
    }


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
            idx = (initialIndex + probe) & mask;
            probe++;
        }
        return null;
    }

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
                HTIndex = (initialIndex + probe) & mask;
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
                HTIndex = (initialIndex + probe) & mask;
                probe++;
            }
            hashTable[HTIndex] = new MapEntry<>(key, value);
        }
        size++;
        return value;
    }


    public ArrayList<MapEntry<K, V>> toList() {
        ArrayList<MapEntry<K, V>> list = new ArrayList<>();
        for (MapEntry<K, V> e : hashTable) {
            if (e != null) {
                list.add(e);
            }
        }
        return list;
    }


    public String toString() {
        return toList().toString();
    }

    /**
     * @return stats[0]=capacity, [1]=size, [2]=collisions,
     *         [3]=#clusters, [4]=largest cluster, [5]=smallest cluster
     */

    public int[] getCharacteristics() {
        int[] stats = new int[6];
        stats[0] = hashTable.length;
        stats[1] = size;
        stats[2] = collisions;

        int clusters = 0;
        int max = 0, min = Integer.MAX_VALUE, curr = 0;
        boolean inCluster = false;

        for (MapEntry<K, V> e : hashTable) {
            if (e != null) {
                if (!inCluster) {
                    inCluster = true;
                    clusters++;
                    curr = 1;
                } else {
                    curr++;
                }
            } else if (inCluster) {
                max = Math.max(max, curr);
                min = Math.min(min, curr);
                inCluster = false;
            }
        }
        if (inCluster) {
            max = Math.max(max, curr);
            min = Math.min(min, curr);
        }
        if (clusters == 0) min = 0;

        stats[3] = clusters;
        stats[4] = max;
        stats[5] = min;
        return stats;
    }
}
