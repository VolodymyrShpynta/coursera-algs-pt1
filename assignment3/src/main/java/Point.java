import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        return this.y == that.y ? this.x - that.x : this.y - that.y;
    }

    public double slopeTo(Point that) {
        if (this.x == that.x && this.y != that.y) {
            return Double.POSITIVE_INFINITY;
        } else if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        } else if (that.y == this.y) {
            return 0.0;
        }
        return (double) (that.y - this.y) / ((double) (that.x - this.x));
    }

    public Comparator<Point> slopeOrder() {
        return (o1, o2) -> {
            double slope1 = Point.this.slopeTo(o1);
            double slope2 = Point.this.slopeTo(o2);

            return Double.compare(slope1, slope2);
        };
    }
}
