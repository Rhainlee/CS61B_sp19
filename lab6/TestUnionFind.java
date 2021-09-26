import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {
    @Test
    public void someTest(){
        UnionFind arb = new UnionFind(4);
        arb.union(0, 1);
        assertEquals(1, arb.find(0));
        assertEquals(-2, arb.parent(1));
        assertEquals(2, arb.sizeOf(1));
        arb.union(2, 3);
        arb.union(0, 3);
        assertEquals(4, arb.sizeOf(0));
        assertEquals(3, arb.find(0));
        assertEquals(true, arb.connected(0, 3));

    }
}
