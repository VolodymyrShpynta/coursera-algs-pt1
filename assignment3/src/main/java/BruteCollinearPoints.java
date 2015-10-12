import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {

    private List<LineSegment> segments = new LinkedList<>();


    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        Point[] pointsCopy = new Point[points.length];
        System.arraycopy(points, 0, pointsCopy, 0, points.length);
        Arrays.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length - 1; i++)
            if (pointsCopy.length > 1 && pointsCopy[i].compareTo(pointsCopy[i + 1]) == 0)
                throw new IllegalArgumentException();

        for (int i = 0; i < pointsCopy.length; i++) {
            if (pointsCopy[i] == null) throw new NullPointerException();
            for (int j = i + 1; j < pointsCopy.length; j++) {
                if (pointsCopy[j] == null) throw new NullPointerException();
                if (pointsCopy[i].equals(pointsCopy[j])) continue;
                for (int k = j + 1; k < pointsCopy.length; k++) {
                    if (pointsCopy[k] == null) throw new NullPointerException();
                    if (pointsCopy[j].equals(pointsCopy[k])) continue;
                    for (int l = k + 1; l < pointsCopy.length; l++) {
                        if (pointsCopy[l] == null) throw new NullPointerException();
                        if (pointsCopy[k].equals(pointsCopy[l])) continue;
                        if (Double.compare(pointsCopy[i].slopeTo(pointsCopy[j]), pointsCopy[i].slopeTo(pointsCopy[k])) == 0 &&
                                Double.compare(pointsCopy[i].slopeTo(pointsCopy[k]), pointsCopy[i].slopeTo(pointsCopy[l])) == 0)
                            segments.add(new LineSegment(
                                            min(pointsCopy[i], pointsCopy[j], pointsCopy[k], pointsCopy[l]),
                                            max(pointsCopy[i], pointsCopy[j], pointsCopy[k], pointsCopy[l]))
                            );
                    }
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
