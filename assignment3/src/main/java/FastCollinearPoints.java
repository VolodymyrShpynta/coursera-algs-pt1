import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private final static int MIN_COLLINEAR_COUNT = 3;
    private List<LineSegment> segments = new LinkedList<>();

    public FastCollinearPoints(Point[] points) {
        if (null == points) throw new NullPointerException();
        Point[] pointsCopy = new Point[points.length];
        System.arraycopy(points, 0, pointsCopy, 0, points.length);
        Arrays.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length - 1; i++)
            if (pointsCopy.length > 1 && pointsCopy[i].compareTo(pointsCopy[i + 1]) == 0)
                throw new IllegalArgumentException();

        for (int i = 0; i < pointsCopy.length - 1; i++) {


            Arrays.sort(pointsCopy, i + 1, pointsCopy.length, pointsCopy[i].slopeOrder());
            int collinearCount = 1;

            Point min = pointsCopy[i];
            Point max = pointsCopy[i];
            for (int j = i + 1; j < pointsCopy.length - 1; j++) {
                if (pointsCopy[i].compareTo(pointsCopy[j]) == 0) continue;
                if (Double.compare(pointsCopy[i].slopeTo(pointsCopy[j]), pointsCopy[i].slopeTo(pointsCopy[j + 1])) == 0) {
                    min = min(min, pointsCopy[j], pointsCopy[j + 1]);
                    max = max(max, pointsCopy[j], pointsCopy[j + 1]);
                    collinearCount++;
                    if (j + 1 == pointsCopy.length - 1 && collinearCount >= MIN_COLLINEAR_COUNT) {
                        segments.add(new LineSegment(min, max));
                    }
                } else if (collinearCount >= MIN_COLLINEAR_COUNT) {
                    segments.add(new LineSegment(min, max));
                    min = pointsCopy[i];
                    max = pointsCopy[i];
                    collinearCount = 1;
                } else {
                    min = pointsCopy[i];
                    max = pointsCopy[i];
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
