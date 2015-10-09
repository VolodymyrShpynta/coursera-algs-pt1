import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {

    private List<LineSegment> segments = new LinkedList<>();


    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        int notEqualsCount = 0;
        for (int i = 0; i < points.length - 1; i++)
            if (!points[i].equals(i + 1))
                notEqualsCount++;
        if(notEqualsCount == 0)
            throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new NullPointerException();
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null) throw new NullPointerException();
                if (points[i].equals(points[j])) continue;
                for (int k = j + 1; k < points.length; k++) {
                    if (points[k] == null) throw new NullPointerException();
                    if (points[j].equals(points[k])) continue;
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[l] == null) throw new NullPointerException();
                        if (points[k].equals(points[l])) continue;
                        if (Double.compare(points[i].slopeTo(points[j]), points[i].slopeTo(points[k])) == 0 &&
                                Double.compare(points[i].slopeTo(points[k]), points[i].slopeTo(points[l])) == 0)
                            segments.add(new LineSegment(
                                            min(points[i], points[j], points[k], points[l]),
                                            max(points[i], points[j], points[k], points[l]))
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
