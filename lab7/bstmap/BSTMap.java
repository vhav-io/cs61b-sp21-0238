package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    int size = 0;
    BSTNode root;
    private class BSTNode{
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        public BSTNode(K key, V value){
            this.key = key;
            this.value = value;
        }
    }


    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containHelp(root, key);

    }
    private boolean containHelp(BSTNode node, K key){
        if(node == null){
            return false;
        }
        if(node.key.equals(key)){
            return true;
        }
        if (key.compareTo(node.key) < 0){
            return containHelp(node.left, key);
        }
        if(key.compareTo(node.key) > 0){
            return containHelp(node.right, key);
        }
        return false;
    }

    @Override
    public V get(K key) {
        return getHealper(root, key);
    }
    private V getHealper(BSTNode node, K key){
        if (node == null){
            return null;
        }
        if (node.key.equals(key)){
            return node.value;
        }
        if (key.compareTo(node.key) < 0){
            return getHealper(node.left, key);
        }
        if(key.compareTo(node.key) > 0){
            return getHealper(node.right, key);
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(root, key, value);


    }
    private BSTNode putHelper(BSTNode node, K key, V value){
        if (node == null){
            size+=1;
            return new BSTNode(key, value);
        }
        if(key.equals(node.key)){
            node.value = value;
        }
        if (key.compareTo(node.key) < 0){
            node.left = putHelper(node.left, key, value);
        }
        if (key.compareTo(node.key) > 0){
            node.right = putHelper(node.right, key, value);
        }
        return node;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }
}
