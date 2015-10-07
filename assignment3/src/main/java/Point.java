import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public final class Point implements Comparable<Point> {
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
        return (double) (this.y - that.y) / ((double) (this.x - that.x));
    }

    public Comparator<Point> slopeOrder() {
        return (o1, o2) -> (o1.y - y) / (o1.x - x) - (o2.y - y) / (o2.x - x);
    }
}
