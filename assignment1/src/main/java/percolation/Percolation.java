package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public abstract class Percolation {

    private final int topSite;
    private final int bottomSite;

    private final boolean[][] grid;
    private final WeightedQuickUnionUF quStructure;
    private final int gridDimension;

    public Percolation(int N) {
        if (N < 1) {
            throw new IllegalArgumentException();
        }
        gridDimension = N;
        grid = new boolean[N][N];
        quStructure = new WeightedQuickUnionUF(N * N + 2);
        topSite = N * N;
        bottomSite = N * N + 1;
    }

    public void open(int i, int j) {
        validateCoordinates(i, j);
        if (isOpen(i, j)) {
            return;
        }

        if (i == 1) {
            openGridSite(i, j);
            quStructure.union(topSite, linearizeCoords(i, j));
        }
        if (i == gridDimension) {
            openGridSite(i, j);
            quStructure.union(bottomSite, linearizeCoords(i, j));
        }
  /*      if(){

        }*/


    }

    public boolean isOpen(int i, int j) {
        validateCoordinates(i, j);
        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        validateCoordinates(i, j);
        return isOpen(i, j) && quStructure.connected(topSite, linearizeCoords(i, j));
    }

    public boolean percolates() {
        return quStructure.connected(topSite, bottomSite);
    }

    private void validateCoordinates(int i, int j) {
        if (i < 1 || j < 1 || i > gridDimension || j > gridDimension) {
            throw new IllegalArgumentException();
        }
    }

    private int linearizeCoords(int i, int j) {
        return gridDimension * (i - 1) + j - 1;
    }

    private void openGridSite(int i, int j) {
        grid[i - 1][j - 1] = true;
    }
}
