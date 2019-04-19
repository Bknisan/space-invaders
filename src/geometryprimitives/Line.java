package geometryprimitives;
import complexobjects.Velocity;

import java.util.List;

/**
 * the line class.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * @param x1 the x value of the starting point
     * @param y1 the y value of the starting point
     * @param x2 the x value of the end
     * @param y2 the y value of the end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        Point middle = new Point((start.getX() + end.getX())
                / 2, (start.getY() + end.getY()) / 2);
        return middle;
    }

    /**
     * @return the starting point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param other Line to check the interasaction with
     * @return true or false depending if there is a meeting or not
     */
    public boolean isIntersecting(Line other) {
        Point test = intersectionWith(other);
        return test != null;
    }

    /**
     * @param other Line to check the intersaction with.
     * @return the value of the meeting point or null if there is no meeting
     */
    public Point intersectionWith(Line other) {
        double denom = (other.end.getY() - other.start.getY()) * (this.end.getX() - this.start.getX())
                - (other.end.getX() - other.start.getX()) * (this.end.getY() - this.start.getY());
        if (denom == 0) { // Lines are parallel.
            return null;
        }
        double ua = ((other.end.getX() - other.start.getX()) * (this.start.getY() - other.start.getY())
                - (other.end.getY() - other.start.getY()) * (this.start.getX() - other.start.getX())) / denom;
        double ub = ((this.end.getX() - this.start.getX()) * (this.start.getY() - other.start.getY())
                - (this.end.getY() - this.start.getY()) * (this.start.getX() - other.start.getX())) / denom;
        if (ua >= 0.0f && ua <= 1.0f && ub >= 0.0f && ub <= 1.0f) {
            // Get the intersection point.
            return new Point((int) (this.start.getX() + ua * (this.end.getX() - this.start.getX())),
                    (int) (this.start.getY() + ua * (this.end.getY() - this.start.getY())));
        }
        return null;
    }

    /**
     * function that check if the line is the same line like received.
     *
     * @param other line to compare with.
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start)) {
            if (this.end.equals(other.end)) {
                return true;
            }
        } else if (this.start.equals(other.end)) {
            if (this.end.equals(other.start)) {
                return true;
            }
        }
        return false;
    }

    /**
     * function that giving back that closest intersection point betwwen a line to a rectangle.
     *
     * @param rect a generic rectangle.
     * @return closest point of intersection between the line and the received rect
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List intersections = rect.intersectionPoints(new Line(this.start, this.end));
        if (intersections.isEmpty()) {
            return null;
        }
        int smallestDistanceIndex = 0;
        double smallestDistance = this.start.distance((Point) intersections.get(0));
        for (int i = 0; i < intersections.size(); i++) {
            if (((Point) intersections.get(i)).distance(this.start) < smallestDistance) {
                smallestDistance = ((Point) intersections.get(i)).distance(this.start);
                smallestDistanceIndex = i;
            }
        }
        return (Point) intersections.get(smallestDistanceIndex);
    }

    /**
     * check if the point is between the max/mix X/Y directions of the collidable block.
     *
     * @param point collision point
     * @return true/false if the point is between the specifications
     */
    public boolean isThePointThere(Point point) {
        return ((point.getX() <= Math.max(start.getX(), end.getX()))
                && (point.getX() >= Math.min(start.getX(), end.getX()))
                && (point.getY() <= Math.max(start.getY(), end.getY()))
                && (point.getY() >= Math.min(start.getY(), end.getY())));
    }

    /**
     * functions that creates a new line based on the ball current center and its speed.
     *
     * @param v      ball speed
     * @param center ball center
     * @param r      ball radius
     * @return line as ball expected to move with no obstacles
     */
    public Line makeTrajectory(Velocity v, Point center, double r) {
        if (v.xDirection() >= 0 && v.yDirection() < 0) {
            return new Line(new Point(center.getX(), center.getY()),
                    new Point(center.getX() + v.xDirection() + r, center.getY() + v.yDirection() - r));
        }
        if (v.xDirection() >= 0 && v.yDirection() >= 0) {
            return new Line(new Point(center.getX(), center.getY()),
                    new Point(center.getX() + v.xDirection() + r, center.getY() + v.yDirection() + r));
        }
        if (v.xDirection() < 0 && v.yDirection() >= 0) {
            return new Line(new Point(center.getX(), center.getY()),
                    new Point(center.getX() + v.xDirection() - r, center.getY() + v.yDirection() + r));
        }
        return new Line(new Point(center.getX(), center.getY()),
                new Point(center.getX() + v.xDirection() - r, center.getY() + v.yDirection() - r));
    }
}
