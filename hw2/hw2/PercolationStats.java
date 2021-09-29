package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import java.util.Random;

import static java.lang.Math.sqrt;

public class PercolationStats {
    private Percolation p;
    private int times;
    private double[] threshold;


    public PercolationStats(int N, int T, PercolationFactory pf) {     // perform T independent experiments on an N-by-N grid
        validate(N, T);
        times = T;
        threshold = new double[T];
        for (int i = 0; i < T; i++) {
            p = pf.make(N);                             //Initialize all sites to be blocked.
            while (!p.percolates()) {
                int rndRow = StdRandom.uniform(N);
                int rndCol = StdRandom.uniform(N);
                while (p.isOpen(rndRow, rndCol)) {
                    rndRow = StdRandom.uniform(N);
                    rndCol = StdRandom.uniform(N);
                }
                p.open(rndRow, rndCol);
            }
            threshold[i] = p.numberOfOpenSites() / ((double) (N * N));
        }

    }

    public double mean() {                                             // sample mean of percolation threshold
        return StdStats.mean(threshold);
    }

    public double stddev() {                                           // sample standard deviation of percolation threshold
        return StdStats.stddev(threshold);
    }

    public double confidenceLow() {                                    // low endpoint of 95% confidence interval
        return mean() - 1.96 * sqrt(stddev()) / sqrt(times);
    }

    public double confidenceHigh() {                                   // high endpoint of 95% confidence interval
        return mean() + 1.96 * sqrt(stddev()) / sqrt(times);
    }

    private void validate(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("invalid argument.");
        }

    }
}
