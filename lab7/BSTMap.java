import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private Node root;
    private class Node{
        private K key;
        private V value;
        private Node left, right;   //left and right subtrees
        private int size;           //number of nodes in subtree

        public Node(K k, V v, int s){
            this.key = k;
            this.value = v;
            this.size = s;
        }
    }
    public BSTMap(){

    }

    /** Removes all of the mappings from this map. */
    public void clear(){
        root = null;
    };

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        return containsKey(root, key);
    };
    private boolean containsKey(Node n, K key){
        if(n == null){
            return false;
        }
        int cmp = n.key.compareTo(key);
        if (cmp > 0) {
            return containsKey(n.left, key);
        }
        if (cmp < 0) {
            return containsKey(n.right, key);
        }
        return true;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return get(root, key);
    };

    private V get(Node n, K key) {
        if (n == null) {
            return null;
        }

        int cmp = key.compareTo(n.key);
        if (cmp < 0 ) {
            return get(n.left, key);
        }
        if (cmp > 0 ) {
            return get(n.right, key);
        }
        return n.value;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){
        return size(root);
    };
    private int size(Node n){
        if (n == null) {
            return 0;
        }
        return n.size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        root = put(root, key, value);
    };
    private Node put(Node n, K key, V value){
        if (n == null){
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = put(n.left, key, value);
        } else if (cmp > 0) {
            n.right = put(n.right, key, value);
        }
        else n.value = value;               //if cmp ==0,override the value
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }
    /*prints out BSTMap in order of increasing Key. */
    public void printInOrder(){
        printHelper(root);
    }
    private void printHelper(Node n){
        if (n == null) {
            return;
        }
        printHelper(n.left);
        System.out.print(n.key + ":" + n.value + "\t");
        printHelper(n.right);
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        throw new UnsupportedOperationException("UnsupportedOperation");
    };

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
        throw new UnsupportedOperationException("UnsupportedOperation");
    };

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
        throw new UnsupportedOperationException("UnsupportedOperation");
    };

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("UnsupportedOperation");
    }
}
