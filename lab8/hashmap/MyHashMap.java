package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private double loadFactor;
    private HashSet<K> keys;
    // You should probably define some more!
    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.size = 0;
        this.loadFactor = maxLoad;
        this.keys = new HashSet<>();

        // 1. Create the array of buckets
        this.buckets = createTable(initialSize);

        // 2. IMPORTANT: You must initialize an empty LinkedList in EVERY array slot.
        // If you don't do this, you will get NullPointerExceptions later!
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        // 1. Reset size to 0
        this.size = 0;
        // 2. Clear out our set of keys
        this.keys = new HashSet<>();
        // 3. Throw away the old array and make a fresh, empty one of the same length
        this.buckets = createTable(16);
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    @Override
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }

        // 1. Sahi locker (bucket) ka index nikalo
        int index = getIndex(key);

        // 2. Us locker ki LinkedList mein iterate karo
        for (Node node : buckets[index]) {
            // Agar key match ho jaye, toh value return kar do
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }
    private int getIndex(K key) {
        // Math.floorMod handles negative hash codes automatically
        return Math.floorMod(key.hashCode(), buckets.length);
    }
    private void resize() {
        // 1. Ek naya array banao jo purane se double size ka ho
        Collection<Node>[] newBuckets = createTable(buckets.length * 2);

        // 2. Har naye locker ko empty LinkedList se initialize karo
        for (int i = 0; i < newBuckets.length; i++) {
            newBuckets[i] = createBucket();
        }

        // 3. Purane array ka saara saaman (nodes) naye array mein dalo
        // Hamara 'keys' set yahan fir se magic karega, loop lagana easy ho jayega!
        for (K key : keys) {
            V value = get(key); // Purani value nikalo
            int newIndex = Math.floorMod(key.hashCode(), newBuckets.length); // Naya index nikalo
            newBuckets[newIndex].add(createNode(key, value)); // Naye array mein daal do
        }

        // 4. Apne main variable ko naye array pe point karwa do
        this.buckets = newBuckets;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        int index = getIndex(key);

        // Case 1: Agar key already exist karti hai, toh sirf value update karo aur wapas jao
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return; // Kaam khatam
            }
        }

        // Case 2: Agar key nahi mili, matlab ye nayi entry hai. Ise add karo.
        buckets[index].add(createNode(key, value));
        keys.add(key); // Apne keySet cheat-code mein bhi add kar lo
        size++; // Total item count badha do

        // Case 3: Load Factor check. Agar limit cross ho gayi, toh resize trigger karo.
        // (double) lagana zaroori hai warna Java integer division karke zero bana dega
        double currentLoad = (double) size / buckets.length;
        if (currentLoad > loadFactor) {
            resize();
        }
    }

    @Override
    public Set<K> keySet() {
        return this.keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return this.keys.iterator();
    }

}
