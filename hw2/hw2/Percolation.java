package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF sites;
    private WeightedQuickUnionUF sitesNoBackWash;
    private boolean[][] blocked;
    private int virtualTopSite;
    private int virtualBottomSite;
    private int numberOfOpenSites;
    private int gridSize;

    public Percolation(int N) {               // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("invalid argument.");
        }
        sites = new WeightedQuickUnionUF(N * N + 2);
        sitesNoBackWash = new WeightedQuickUnionUF(N * N + 1);
        gridSize = N;
        blocked = new boolean[N][N];
        for (boolean[] b : blocked) {            //all grids are blocked.
            for (int i = 0; i < b.length; i++) {
                b[i] = true;
            }
        }
        virtualTopSite = N * N;
        virtualBottomSite = N * N + 1;
        numberOfOpenSites = 0;
    }

    private int xyTo1D(int row, int col) {
        return row * gridSize + col;
    }

    public void open(int row, int col) {      // open the site (row, col) if it is not open already
        validate(row, col);
        if (blocked[row][col]) {
            blocked[row][col] = false;
            numberOfOpenSites += 1;
        }
        if (row == 0) {
            sites.union(xyTo1D(row, col), virtualTopSite);
            sitesNoBackWash.union(xyTo1D(row, col), virtualTopSite);
        }
        if (row == blocked.length - 1) {
            sites.union(xyTo1D(row, col), virtualBottomSite);
        }
        if (row > 0 && isOpen(row - 1, col)) {                       //connect top side.
            sites.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            sitesNoBackWash.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        if (row < blocked.length - 1 && isOpen(row + 1, col)) {      //connect down side.
            sites.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            sitesNoBackWash.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {                        //connect left side.
            sites.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            sitesNoBackWash.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
        if (col < blocked.length - 1 && isOpen(row, col + 1)) {       //connect right side.
            sites.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            sitesNoBackWash.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }

    }

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        validate(row, col);
        return !blocked[row][col];
    }

    public boolean isFull(int row, int col) { // is the site (row, col) full?
        validate(row, col);
        if (sitesNoBackWash.connected(xyTo1D(row, col), virtualTopSite)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {          // number of open sites
        return numberOfOpenSites;
    }

    public boolean percolates() {             // does the system percolate?
        if (sites.connected(virtualTopSite, virtualBottomSite)) {
            return true;
        }
        return false;
    }

    private void validate(int row, int col) {
        if (row < 0 || row > blocked.length - 1) {
            throw new java.lang.IndexOutOfBoundsException("invalid index.");
        }
        if (col < 0 || col > blocked.length - 1) {
            throw new java.lang.IndexOutOfBoundsException("invalid index.");
        }
    }


    public static void main(String[] args) {  // use for unit testing (not required, but keep this here for the autograder)
        Percolation p = new Percolation(6);
        p.open(0, 5);
        p.open(1, 5);
        p.open(2, 5);
        p.open(3, 5);
        p.open(4, 5);
        p.open(4, 4);
        p.open(3, 3);
        p.open(2, 3);
        p.open(1, 3);
        p.open(1, 2);
        if (!p.isFull(1, 2)) {
            System.out.println("testIsFull passed!");
        } else {
            System.out.println("testIsFull not passed!");
        }
        if (!p.percolates()) {
            p.open(5, 4);
            if (p.percolates()) {
                System.out.println("testPercolates passed!");
            } else {
                System.out.println("testPercolates not passed!");
            }
        } else {
            System.out.println("testPercolates step 1 not passed!");
        }
        if (p.numberOfOpenSites() == 11) {
            System.out.println("testNumberOfOpenSites passed!");
        }

    }
}
