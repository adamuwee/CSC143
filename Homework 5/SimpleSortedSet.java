import java.util.*;
/**
 * Uses a Linked list so that an element can be added anywhere in the array without 
 * a lot of iterations
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SimpleSortedSet<E> implements SortedSet<E>
{

    // Object Array
    private Object[] items;
    
    // Array Size
    private int size = 0;

    // Comparator
    private Comparator comp;
    
    // Parent SortedSet
    SimpleSortedSet parent;
    
    // Child SimpleSortedSet
    SimpleSortedSet[] child;
    
    
   /**
    * Constructors
    */
    public SimpleSortedSet(int i){
          if (i < 0)
            throw new IllegalArgumentException("Negative initial capacity="+ i);
        this.items = new Object[i];
    }
    
    public SimpleSortedSet(){
        this(10);     // Default Size
    }
    
    public SimpleSortedSet(Collection c) {
        items = c.toArray();
    }
    
    public SimpleSortedSet(Comparator<E> c) {
        comp = c;
    }
    

    
    /**
     * ********* Class Methods ***********
     */
    /**
     * 
     * @param fromElement
     * @param toElement
     * @return
     */
    public SortedSet subSet(E fromElement, E toElement) {

        SimpleSortedSet subSet = new SimpleSortedSet();
        int index = 0;
        int limit = getIndexInSortedSet(toElement);
        if ( getIndexInSortedSet(fromElement) < 0) {
            index = 0;
        }
        if (  getIndexInSortedSet(toElement) < 0 ) {
            limit = size;
        }
        for(int i = index; i < limit; i++){
        subSet.add(items[i]);
    }
    return (SortedSet)subSet;
    }  
    
        /**
     * Add an Element to the Array
     */
    public boolean add(E o) {
        // Check if the array already contains item
        if (contains(o)){
            return false; // A set has no duplicates
        }  
        // 
        
        // Find the location where the new object should be inserted
        // Iterate through loop to find index where new object belongs
        int index = 0;
        for(int i = 0; i < size; i++) {
            if (compare(o,(E)items[i]) > 0)  {
                index = i+1;
            }
        }

        // Now that the index is known, we know where to insert the object
        // We will start at the end of the new array and work backwards
        
        // Create a new Array that is one element larger
        Object[] temp = new Object[size+1];
        
        // Copy items onto temp
        for( int i = 0; i < size+1; i++) {
         // Copy array elements that go before the new element
         if (i < index)
         temp[i] = items[i];
         
         // Copy the new element into temp array
         if (i == index)
         temp[i] = o;
         
         // Copy array elements that go after the new element
         if (i > index)
         
         temp[i] = items[i-1];
        }
        
        this.size++;        
        this.items = temp;

        return true;
    }
    
    /**
     * Clear the Current Array
     */
    public void clear() {
        items = null;
        this.size = 0;
    }   
    /**
     * Remove an Object from the Array
     */
    public boolean remove(Object o) {
        int index = getIndexInSortedSet(o);
                if (index < 0 || index >= this.size) {
                    return false;
            //throw new IndexOutOfBoundsException("Index=" + index);
        }
        // compact the array
        for (int i = index + 1; i < size; i++)
            this.items[i - 1] = this.items[i];
        size--;
        // let's gc do its work
        this.items[size] = null;
        return true;  
    }
    /**
     * Return the Size of the Array
     */
    public int size() { 
        return size;
    }
    
    /**
     * Returns whether or not the Array is empty
     */
    public boolean isEmpty() {  
        return (this.size == 0);
    }
    
    public boolean contains(Object o) { 
        return this.getIndexInSortedSet(o) >= 0;
    }
    
    public int getIndexInSortedSet(Object o) {
        // If o is null
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (this.items[i] == null)
                    return i;
        } else // o is an object
        {
            for (int i = 0; i < size; i++)
                if (o.equals(this.items[i]))
                    return i;
        }
        // o is not in the list
        return -1;  
    }
    
    public int compare(E e1, E e2) {
        if (comp == null) {
        // Compare the toString method of each object
        int i = ((Comparable<E>) e1).compareTo(e2);
        return i;
    }
    else {
        return comp.compare(e1,e2);
    }
    }
    public E get(int i) {
        return (E)items[i];
    }
    
    /**
     * SortedSet Iterator
     * @author DogChow
     */
    private class MyIterator implements Iterator<E> {
        private int index = 0;

        private SimpleSortedSet<E> list;

        private int lastIndex = -1; // index of the object most recently by next

        /**
         * Create an iterator for a MyArrayList
         */
        public MyIterator(SimpleSortedSet<E> list) {
            this.list = list;
        }

        /**
         * Any element left in the list?
         */
        public boolean hasNext() {
            return index < this.list.size;
        }

        /**
         * Return the current element in the list and move to the next element
         */
        public E next() {
            if (!this.hasNext())
                throw new NoSuchElementException();
            this.lastIndex = index;
            return (E) this.list.items[index++]; // Warning!
        }

        /**
         * Remove the last object returned by next
         */
        public void remove() {
            if (this.lastIndex == -1)
                throw new IllegalStateException();
            this.list.remove(list.get(lastIndex));
            this.index--;
            this.lastIndex = -1;
        }
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * 
     * @return an iterator over the elements in this list in proper sequence.
     */
    public Iterator<E> iterator() {
        return new MyIterator(this);
    }
    
    
    
    
    
    
    
    
    /**
     * UNSUPPORTED METHODS
     * THROWS UNSUPPORTEDOPERATIONEXCEPTION
     */
    public Comparator comparator()  {
        throw new UnsupportedOperationException();      
    }
    public E first() {
        throw new UnsupportedOperationException();      
    }
    public SortedSet headSet(E toElement) {
        throw new UnsupportedOperationException();       
    }
    public E last() {
        throw new UnsupportedOperationException();   
    } 
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }
    public Object[] toArray(Object[] arg0) {
        throw new UnsupportedOperationException();
    }
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException(); 
    }
    public boolean remove(Collection c) {
        throw new UnsupportedOperationException(); 
    }
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();      
    }
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }
    public boolean add(Collection c) {
        throw new UnsupportedOperationException();   
    }
    public SortedSet tailSet(E fromElement) {
        throw new UnsupportedOperationException();   
    }

}
