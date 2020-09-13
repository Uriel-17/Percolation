import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// Models an N-by-N percolation system.
public class Percolation {

    // Create an N-by-N grid, with all sites blocked.
    //...
    public Percolation(int N) {
        //...
    }

    // Open site (row, col) if it is not open already.
    public void open(int row, int col) {
        //...
    }

    // Is site (row, col) open?
    public boolean isOpen(int row, int col) {
        //...
        return false;
    }

    // Is site (row, col) full?
    public boolean isFull(int row, int col) {
        //...
        return false;
    }

    // Number of open sites.
    public int numberOfOpenSites() {
        //...
        return 0;
    }

    // Does the system percolate?
    public boolean percolates() {
        //...
        return false;
    }

    // An integer ID (1...N) for site (row, col).
    private int encode(int row, int col) {
        //...
        return 0;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.println(perc.numberOfOpenSites() + " open sites");
        if (perc.percolates()) {
            StdOut.println("percolates");
        }
        else {
            StdOut.println("does not percolate");
        }

        // Check if site (i, j) optionally specified on the command line
        // is full.
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(perc.isFull(i, j));
        }
    }
}
