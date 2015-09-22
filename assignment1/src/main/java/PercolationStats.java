import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int gridSize;
    private final int numberOfExperiments;
    private final double[] experimentResults;

    public PercolationStats(int N, int T) {
        gridSize = N;
        numberOfExperiments = T;
        experimentResults = new double[numberOfExperiments];

        for (int loop = 0; loop < numberOfExperiments; loop++) {
            Percolation percolation = new Percolation(gridSize);
            int experimentResult = 0;
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(1, gridSize + 1);
                int j = StdRandom.uniform(1, gridSize + 1);
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    experimentResult++;
                }
            }
            double fraction = experimentResult / (double)(gridSize * gridSize);
            experimentResults[loop] = fraction;
        }

    }

    public double mean() {
        return StdStats.mean(experimentResults);
    }

    public double stddev() {
        return StdStats.stddev(experimentResults);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(numberOfExperiments);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(numberOfExperiments);
    }

    public static void main(String[] args) {
        int gridSize = Integer.parseInt(args[0]);
        int numberOfExperiments = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(gridSize, numberOfExperiments);
        StdOut.printf("mean                    =%f\n", percolationStats.mean());
        StdOut.printf("stddev                  =%f\n", percolationStats.stddev());
        StdOut.print("95% ");
        StdOut.printf("confidence interval =%f, %f\n", percolationStats.confidenceLo(), percolationStats.confidenceHi());

    }
}
