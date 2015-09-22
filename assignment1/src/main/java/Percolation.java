import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int topSite;
    private final int bottomSite;

    private final boolean[][] grid;
    private final WeightedQuickUnionUF quStructure;
    private final WeightedQuickUnionUF backWash;
    private final int gridDimension;

    public Percolation(int N) {
        if (N < 1) {
            throw new IllegalArgumentException();
        }
        gridDimension = N;
        grid = new boolean[N][N];
        quStructure = new WeightedQuickUnionUF(N * N + 2);
        backWash = new WeightedQuickUnionUF(N * N + 1);
        topSite = N * N;
        bottomSite = N * N + 1;
    }

    public void open(int i, int j) {
        validateCoordinates(i, j);
        if (isOpen(i, j)) {
            return;
        }
        openGridSite(i, j);
        connectTop(i, j);
        connectBottom(i, j);
        connectLeft(i, j);
        connectRight(i, j);
        connectDown(i, j);
        connectUp(i, j);
    }

    private void connectTop(int i, int j) {
        if (i == 1) {
            backWash.union(topSite, linearizeCoords(i, j));
            quStructure.union(topSite, linearizeCoords(i, j));
        }
    }

    private void connectBottom(int i, int j) {
        if (i == gridDimension) {
            quStructure.union(bottomSite, linearizeCoords(i, j));
        }
    }

    private void connectLeft(int i, int j) {
        if (isPresentAndOpen(i - 1, j)) {
            backWash.union(linearizeCoords(i - 1, j), linearizeCoords(i, j));
            quStructure.union(linearizeCoords(i - 1, j), linearizeCoords(i, j));
        }
    }

    private void connectRight(int i, int j) {
        if (isPresentAndOpen(i + 1, j)) {
            backWash.union(linearizeCoords(i + 1, j), linearizeCoords(i, j));
            quStructure.union(linearizeCoords(i + 1, j), linearizeCoords(i, j));
        }
    }

    private void connectUp(int i, int j) {
        if (isPresentAndOpen(i, j + 1)) {
            backWash.union(linearizeCoords(i, j + 1), linearizeCoords(i, j));
            quStructure.union(linearizeCoords(i, j + 1), linearizeCoords(i, j));
        }
    }

    private void connectDown(int i, int j) {
        if (isPresentAndOpen(i, j - 1)) {
            backWash.union(linearizeCoords(i, j - 1), linearizeCoords(i, j));
            quStructure.union(linearizeCoords(i, j - 1), linearizeCoords(i, j));
        }
    }

    public boolean isOpen(int i, int j) {
        validateCoordinates(i, j);
        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        validateCoordinates(i, j);
        return isOpen(i, j) && backWash.connected(topSite, linearizeCoords(i, j));
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

    private boolean isPresentAndOpen(int i, int j) {
        return i >= 1 && j >= 1 && i <= gridDimension && j <= gridDimension && isOpen(i, j);
    }
}
