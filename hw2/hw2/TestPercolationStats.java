package hw2;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestPercolationStats {
    @Test
    public void someTest(){
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(5, 30, pf);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceLow());
        System.out.println(ps.confidenceHigh());


    }
}
