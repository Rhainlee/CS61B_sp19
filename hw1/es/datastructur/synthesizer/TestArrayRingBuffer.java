package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author RsLee
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        assertEquals(true, arb.isEmpty());
        arb.enqueue(5.1);
        arb.enqueue(5.2);
        assertEquals(false, arb.isEmpty());
        assertEquals(2, arb.fillCount());
        arb.enqueue(5.3);
        arb.enqueue(5.4);
        assertEquals(true, arb.isFull());
        assertEquals(4, arb.capacity());
        assertEquals(5.1, arb.peek());
        assertEquals(4, arb.fillCount());
        assertEquals(5.1, arb.dequeue());
        assertEquals(3, arb.fillCount());
        assertEquals(false, arb.isFull());

        //test equals method.
        ArrayRingBuffer a1 = new ArrayRingBuffer(4);
        ArrayRingBuffer a2 = new ArrayRingBuffer(4);
        a1.enqueue(5.1);
        a2.enqueue(5.1);
        assertEquals(true, a1.equals(a2));
        assertEquals(5.1, a2.peek());
        assertEquals(5.1, a1.peek());
        a2.enqueue(5.2);
        assertEquals(false, a2.equals(a1));

        //test iteration
        ArrayRingBuffer<String> arr = new ArrayRingBuffer<>(3);
        arr.enqueue("lee");
        arr.enqueue("lee");
        arr.enqueue("lee");

        for (String s:arr) {
            assertEquals("lee", s);
        }

    }
}
