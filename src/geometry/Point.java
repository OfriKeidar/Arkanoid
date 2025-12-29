// 211767561 Ofri Keidar
package geometry;
/**
 * a class that representing point with x,y fields.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * construct a new point with the relevant fields.
     * @param x the x value of the point.
     * @param y the y value of the point.
     */
    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return the  Euclidean distance between this point and other point.
     * @param other the other point.
     * @return the distance of this point to the other point.
     */
    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double x1 = this.x;
        double x2 = other.getX();
        double y1 = this.y;
        double y2 = other.getY();
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    /**
     * check if two points are equal.
     * @param other the other point
     * @return true is the points are equal, false otherwise.
     */

    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        double x2 = other.getX();
        double y2 = other.getY();
        return (this.x == x2) && (this.y == y2);
    }

    /**
     * gets the x-coordinate of the point.
     * @return the x-coordinate of the point.
     */

    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }
    /**
     * gets the y-coordinate of the point.
     * @return the y-coordinate of the point.
     */
    public double getY() {
        return this.y;
    }
}
