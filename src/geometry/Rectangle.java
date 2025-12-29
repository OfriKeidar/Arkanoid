// 211767561 Ofri Keidar
package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * representing a rectangle by his edge points and lines.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * constructor to the rectangle fields.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * in this method we return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line we checked intersection points with the rectangle.
     * @return the list of intersection points with the specified line(maybe empty).
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        //building the rect lines
        Line upRectLine = new Line(getUpperLeft(), getUpperRight());
        Line downRectLine = new Line(getDownerLeft(), getDownerRight());
        Line leftRectLine = new Line(getUpperLeft(), getDownerLeft());
        Line rightRectLine = new Line(getUpperRight(), getDownerRight());
        boolean checkUpSideAndLine = upRectLine.isIntersecting(line);
        boolean checkDownSideAndLine = downRectLine.isIntersecting(line);
        boolean checkLeftSideAndLine = leftRectLine.isIntersecting(line);
        boolean checkRightSideAndLine = rightRectLine.isIntersecting(line);
        // Checking intersections points with each line of the rectangle
        if (checkUpSideAndLine) {
            intersectionPoints.add(upRectLine.intersectionWith(line));
        }
        if (checkDownSideAndLine) {
            intersectionPoints.add(downRectLine.intersectionWith(line));
        }
        if (checkLeftSideAndLine) {
            intersectionPoints.add(leftRectLine.intersectionWith(line));
        }
        if (checkRightSideAndLine) {
            intersectionPoints.add(rightRectLine.intersectionWith(line));
        }

        // Return the list of intersection points
        return intersectionPoints;
    }

    /**
     * method to get the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * method to get the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * method to get the upper left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * method to get the upper right point of the rectangle.
     *
     * @return the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        double upperRightX = getUpperLeft().getX() + getWidth();
        double upperRightY = getUpperLeft().getY();
        return new Point(upperRightX, upperRightY);
    }

    /**
     * method to get the downer left point of the rectangle.
     *
     * @return the downer-left point of the rectangle.
     */
    public Point getDownerLeft() {
        double downerLeftX = getUpperLeft().getX();
        double downerLeftY = getUpperLeft().getY() + getHeight();
        return new Point(downerLeftX, downerLeftY);
    }

    /**
     * method to get the downer right point of the rectangle.
     *
     * @return the downer-right point of the rectangle.
     */
    public Point getDownerRight() {
        double downerRightX = getUpperLeft().getX() + getWidth();
        double downerRightY = getDownerLeft().getY();
        return new Point(downerRightX, downerRightY);
    }
}

