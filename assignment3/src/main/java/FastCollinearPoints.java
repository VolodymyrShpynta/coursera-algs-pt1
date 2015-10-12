import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private final static int MIN_COLLINEAR_COUNT = 3;
    private List<LineSegment> segments = new LinkedList<>();

    public FastCollinearPoints(Point[] points) {
        if (null == points) throw new NullPointerException();

        int notEqualsCount = 0;
        for (int i = 0; i < points.length - 1; i++)
            if (!points[i].equals(i + 1))
                notEqualsCount++;
        if (notEqualsCount == 0)
            throw new IllegalArgumentException();

        for (int i = 0; i < points.length - 1; i++) {


            Arrays.sort(points, i + 1, points.length, points[i].slopeOrder());
//            Arrays.sort(points, points[i].slopeOrder());
            int collinearCount = 1;

      /*      for (int j = i + 1; j < points.length; j++) {
                System.out.print(points[j] + ": " + points[i].slopeTo(points[j]) + " ");
            }
            System.out.println();*/

            Point min = points[i];
            Point max = points[i];
            for (int j = i + 1; j < points.length - 1; j++) {
                if (points[i].compareTo(points[j]) == 0) continue;
                if (Double.compare(points[i].slopeTo(points[j]), points[i].slopeTo(points[j + 1])) == 0) {
                    min = min(min, points[j], points[j + 1]);
                    max = max(max, points[j], points[j + 1]);
                    collinearCount++;
                    if (j + 1 == points.length - 1 && collinearCount >= MIN_COLLINEAR_COUNT) {
                        segments.add(new LineSegment(min, max));
                    }
                } else if (collinearCount >= MIN_COLLINEAR_COUNT) {
                    segments.add(new LineSegment(min, max));
                    min = points[i];
                    max = points[i];
                    collinearCount = 1;
                } else {
                    min = points[i];
                    max = points[i];
                    collinearCount = 1;
                }
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

    private Point min(Point... points) {
        Point minPoint = null;
        for (Point point : points) {
            if (minPoint == null) {
                minPoint = point;
            } else {
                minPoint = minPoint.compareTo(point) < 0 ? minPoint : point;
            }
        }
        return minPoint;
    }

    private Point max(Point... points) {
        Point maxPoint = null;
        for (Point point : points) {
            if (maxPoint == null) {
                maxPoint = point;
            } else {
                maxPoint = maxPoint.compareTo(point) < 0 ? point : maxPoint;
            }
        }
        return maxPoint;
    }
}
