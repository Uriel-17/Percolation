import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// Models an N-by-N percolation system.
public class Percolation {

    private boolean grid[][]; // our grid - Note that it is false
    private int openSites; // number of open sites
    private int N; // gives us the size of matrix
    private WeightedQuickUnionUF unionUF;
    private int source;
    private int sink;

    // Create an N-by-N grid, with all sites blocked.
    //...

    /**
     * Connect the sites corresponding to the first and last rows of the percolation
     * system with the source and sink sites respectively.
     * @param N is the size of the n x n matrix
     */
    public Percolation(int N) throws IllegalArgumentException{
        if(N < 0) {

            throw new IllegalArgumentException("Percolation(): N is less than 0");

        }

        this.N = N;
        grid = new boolean[N][N];
        openSites = 0;
        unionUF = new WeightedQuickUnionUF(N); // 2 is for the source and sink
        source = 0;
        sink = (N * N) + 1;

        //note that 0 is our source that is on top
        //of the grid and (n * n) + 1 is the sink at the
        //bottom of the grid
        // we are connecting the upper row and lower row to the source and sink
        for(int i = 0; i < N; i++) {

            unionUF.union(source, encode(0, i));
            unionUF.union(encode(N -1, i), sink);

        }
    }

    // Open site (row, col) if it is not open already.
    /**
     * Checks if any of the neighbors to the north, east, west, and south of (i, j) is open, and if so, connect
     * the site corresponding to (row, col) with the site of the corresponding to that neighbor.
     * @param row controls the index of the row.
     * @param col controls the index of the col
     * @throws IndexOutOfBoundsException checks if row or col are outside its prescribed range
     */
    public void open(int row, int col) throws IndexOutOfBoundsException { // indexofout bounds exception

        if(row < 0 || row > N - 1 || col < 0 || col > N - 1) {

            throw new IndexOutOfBoundsException("open(int row, int col): either row " +
                    "is out of bounds or col is.");
        }

        //checking up case
        if(grid[row - 1][col] == true) {

            unionUF.union(encode(row - 1, col), encode(row, col));
            openSites++;

        }

        //checking below
        if(grid[row + 1][col] == true) {

            unionUF.union(encode(row + 1, col), encode(row, col));
            openSites++;
        }

        //checking left

        if(grid[row][col - 1] == true) {

            unionUF.union(encode(row, col - 1), encode(row, col));
            openSites++;

        }

        //checking right

        if(grid[row][col + 1] == true) {

            unionUF.union(encode(row, col + 1), encode(row, col));
            openSites++;

        }

    }

    // Is site (row, col) open?

    /**
     * returns whether cell (i, j) is open or not
     * @param row controls the index of the row.
     * @param col controls the index of the col
     * @return a boolean if the open site is either open or closed
     * @throws IllegalArgumentException checks if row or col are outside its prescribed range
     */
    public boolean isOpen(int row, int col) throws IllegalArgumentException {

        if(row < 0 || row > N - 1 || col < 0 || col > N - 1) {

            throw new IndexOutOfBoundsException("open(int row, int col): either row " +
                    "is out of bounds or col is.");
        }
        return grid[row][col] == true;
    }

    // Is site (row, col) full?

    /**
     *
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col) {
        //...
        return false;
    }//uses isOpen encode and union.connect

    // Number of open sites.

    /**
     * returns the number of open slots in the matrix
     * @return the number of open sites.
     */
    public int numberOfOpenSites() {

        return openSites;
    }

    // Does the system percolate?

    /**
     * returns whether the system percolates or not; the system
     * percolates if the sink site is connected to the source site
     * @return a boolean if the system percolates
     */
    public boolean percolates() {

        if(unionUF.connected(sink, source)) {

            return true;
        }
        return false;
    }

    // An integer ID (1...N) for site (row, col).
    private int encode(int row, int col) {
        return (N * row) + col + 1;
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
