import java.util.*;
import java.util.Map;


/**
 * Uses a TreeMap to track of each word is the users document
 * @author Open
 *
 */
public class SimpleMap<K,V> implements Map<K,V> {

    // SimpleArrayList
    SimpleSortedSet keys = new SimpleSortedSet();
    // Object Array
    Object[] values;
    // Size
    int size;
    
    // Default Size
    private int DEFAULT_SIZE = 10;
    

    
    public SimpleMap() {
        values = new Object[DEFAULT_SIZE];
        keys = new SimpleSortedSet(DEFAULT_SIZE);
    }
    
    /**
     * Put the key and value into the SimpleMap
     */
    public V put(K key, V value) {
        
        // Check if the key already exists
        if ( containsKey(key) ) {
            
            // if the key is found the value should be added to existing key
            // index of key
            int i = keys.getIndexInSortedSet(key);
            Object v = values[i];
            // Add to the value
            Integer k = new Integer(v.toString());
            k++;
            values[i] = k;
        }
        // If the SimpleSortedSet does not contain the key add it to the Set and add it to the value
        else {
            keys.add(key);
            size++;
            // add it to the value
            int indexOfNewKey = keys.getIndexInSortedSet(key);
            addValue(indexOfNewKey);
        }
        
        return value;
    }
    
    /**
     * Checks if the key is in already in the SimpleMap
     */
    public boolean containsKey(Object key) {
        return keys.contains(key);
    }
    
    /**
     * Gets the value based on the key parameter passed
     */
    public V get(Object key) {
        return (V)values[keys.getIndexInSortedSet(key)];
    }
    
    /**
     * Removes the value based on the key parameter passed
     */
    public V remove(Object key) {
        int i = keys.getIndexInSortedSet(key);
        keys.remove(key);
        return (V)values[i];
    }
    /**
     * returns the key set
     */
    public Set<K> keySet() {
        return keys;
    }
    public int size() {
        return this.size;
    }
    
    public void clear() {
        keys = null;
        values = null;
    }
    
    public boolean isEmpty() {
        return (size == 0);
    }
        
    private void addValue(int index) {
        // Make sure that the index is within the array bounds
        
        if (index < values.length) {
            // if it is within then add the value
            // Add the new value
            values[index] = new Object();
        }
        else {
            // if it isn't then create a new  array with the index at the end of the array
            Object[] temp = new Object[index+1];
            
            // copy values over to new array
            for(int i = 0; i <= index; i ++) {
                temp[i] = values[i];
            }
            // Add the new value
            Object v = temp[index];
            Integer k = new Integer( v.toString() );
            k++;
            temp[index] = k;
            values = temp;
    } 
}

    /**
     * UNSUPPORTED METHODS
     */
    public Collection values() {
        throw new UnsupportedOperationException();  
        
    }
    public Set entrySet() {
        throw new UnsupportedOperationException();  
        
    }
    public boolean containsValue(Object arg0) {
        throw new UnsupportedOperationException();  
    }
    public void putAll(Map arg0) {
        throw new UnsupportedOperationException();  
        
    }
    
}
