package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (fillCount == rb.length) {
            throw new RuntimeException("Ring Buffer overflow.");
        }
        rb[last] = x;
        last = (last + 1) % rb.length;
        fillCount ++;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow.");
        }
        T itemDel = rb[first];
        first = (first + 1) % rb.length;
        fillCount --;
        return itemDel;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        return rb[first];
    }

    /**
     * Return size of the buffer.
     */
    @Override
    public int capacity(){
        return rb.length;
    }

    /**
     * Return number of items currently in the buffer.
     */
    @Override
    public int fillCount(){
        return fillCount;
    }
    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    @Override
    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T>{
        private int ptr;
        public ArrayRingBufferIterator(){
            ptr = 0;
        }

        public boolean hasNext() {
            return ptr < rb.length;
        }

        public T next() {
            T returnItem = rb[ptr];
            ptr ++;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if(o.getClass() != this.getClass()){
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if(this.rb.length != other.rb.length){
            return false;
        }
        for (int i =0; i < this.fillCount; i++) {
            if(!this.rb[i].equals(other.rb[i]))
                return false;
        }
            return true;
    }

}
    // TODO: Remove all comments that say TODO when you're done.
