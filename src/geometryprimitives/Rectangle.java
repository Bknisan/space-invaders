package geometryprimitives;

import java.util.ArrayList;
import java.util.List;

/**
 * the rectangle class.
 */
public class Rectangle {
    private Point upperLeft;
    private Point lowerLeft;
    private Point upperRight;
    private Point lowerRight;
    private double width;
    private double height;

    /**
     * @param upperLeft upper left point of the rect
     * @param width     the width of the rect
     * @param height    the height of the rect
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.width = width;
        this.height = height;
    }

    /**
     * function that receive a line and checks if there are intersection points between it to the rect.
     *
     * @param line some line.
     * @return list of intersection points
     */
    public java.util.List intersectionPoints(Line line) {
        Line upperHorizontal = new Line(getUpperLeft(), getUpperRight());
        Line lowerHorizontal = new Line(getLowerLeft(), getLowerRight());
        Line leftVertical = new Line(getUpperLeft(), getLowerLeft());
        Line rightVertical = new Line(getUpperRight(), getLowerRight());
        List<Point> intersections = new ArrayList<>();
        if (line.intersectionWith(upperHorizontal) != null) {
            intersections.add(line.intersectionWith(upperHorizontal));
        }
        if (line.intersectionWith(leftVertical) != null) {
            intersections.add(line.intersectionWith(leftVertical));
        }
        if (line.intersectionWith(lowerHorizontal) != null) {
            intersections.add(line.intersectionWith(lowerHorizontal));
        }
        if (line.intersectionWith(rightVertical) != null) {
            intersections.add(line.intersectionWith(rightVertical));
        }
        return intersections;
    }

    /**
     * @return the width of the rect.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rect.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper left point of the rect.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the lower left point of the rect.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * @return the upper right point of the rect.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * @return the lower right point of the rect.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

}
