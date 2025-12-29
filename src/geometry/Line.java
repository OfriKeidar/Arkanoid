// 211767561 Ofri Keidar
package geometry;
import java.util.List;

/**
 * A class representing a line segment in a two-dimensional space.
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * constructs a line based on the given points.
     *
     * @param start the line's starting point.
     * @param end   the line's ending point.
     */
    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructs a line based on 4 fields - the first x,y and the second x,y.
     *
     * @param x1 the x-coordinate of the first point.
     * @param y1 the y-coordinate of the first point.
     * @param x2 the x-coordinate of the second point.
     * @param y2 the y-coordinate of the second point.
     */

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * return the length of the line.
     *
     * @return the length of the line.
     */
    // Return the length of the line
    //we use that method in ass2
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line.
     */
    // Returns the middle point of the line
    //we use that method in ass2
    public Point middle() {
        double middleX = (this.start().getX() + this.end().getX()) / 2;
        double middleY = (this.start().getY() + this.end().getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * returns the start point of the line.
     *
     * @return the start point of the line.
     */
    // Returns the start point of the line
    public Point start() {
        return new Point(start.getX(), start.getY());
    }

    /**
     * returns the end point of the line.
     *
     * @return the end point of the line.
     */
    // Returns the end point of the line
    public Point end() {
        return new Point(end.getX(), end.getY());
    }

    /**
     * calculates the gradient of the line,based on basic calculation.
     *
     * @return the slop of the line.
     */
    //returns the slop of the line
    public double slopLine() {
        double counter = start.getY() - end.getY();
        double denominator = start.getX() - end.getX();
        return counter / denominator;
    }

    /**
     * finding the y-intercept in the equation.
     *
     * @return the y-intercept of the given line point.
     */
    public double findYIntercept() {
        return this.start.getY() - slopLine() * this.start.getX();
    }

    /**
     * checking if the difference between given values is less than epsilon.
     *
     * @param p1 the first parameter.
     * @param p2 the second parameter.
     * @return true if the result is less than epsilon,false otherwise.
     */
    public boolean checkSimilarity(double p1, double p2) {
        final double epsilon = 0.0000001;
        if (p2 < p1) {
            return (p1 - p2) <= epsilon;
        } else {
            return (p2 - p1) <= epsilon;
        }
    }

    /**
     * rearranging the start and end fields values is needed, and check if the given point is in the scale.
     *
     * @param point the point we want to check if it is in the values' scale of the line.
     * @param other the point we want to check if it is in the values' scale of the second line.
     * @return true if the intersection point is in the scale,false otherwise.
     */
    public boolean checkPoint(Point point, Line other) {
        double startingX = Math.min(this.start.getX(), this.end.getX());
        double startingY = Math.min(this.start.getY(), this.end.getY());
        double endingX = Math.max(this.start.getX(), this.end.getX());
        double endingY = Math.max(this.start.getY(), this.end.getY());
        double secondStartingX = Math.min(other.start.getX(), other.end.getX());
        double secondStartingY = Math.min(other.start.getY(), other.end.getY());
        double secondEndingX = Math.max(other.start.getX(), other.end.getX());
        double secondEndingY = Math.max(other.start.getY(), other.end.getY());
        boolean firstLineCheck = (point.getX() >= startingX && point.getX() <= endingX)
                && (point.getY() >= startingY && point.getY() <= endingY);
        boolean secondLineCheck = (point.getX() >= secondStartingX && point.getX() <= secondEndingX)
                && (point.getY() >= secondStartingY && point.getY() <= secondEndingY);
        return firstLineCheck && secondLineCheck;
    }

    /**
     * checking inclusion between this line and other line.
     *
     * @param point check if the point is on the line.
     * @param other the other line we want to check on him.
     * @return true if one line concludes another.
     */
    public boolean checkInclusion(Point point, Line other) {
        double startingX = Math.min(this.start.getX(), this.end.getX());
        double startingY = Math.min(this.start.getY(), this.end.getY());
        double endingX = Math.max(this.start.getX(), this.end.getX());
        double endingY = Math.max(this.start.getY(), this.end.getY());
        double secondStartingX = Math.min(other.start.getX(), other.end.getX());
        double secondStartingY = Math.min(other.start.getY(), other.end.getY());
        double secondEndingX = Math.max(other.start.getX(), other.end.getX());
        double secondEndingY = Math.max(other.start.getY(), other.end.getY());
        boolean firstLineCheck = (point.getX() > startingX && point.getX() < endingX)
                && (point.getY() > startingY && point.getY() < endingY);
        boolean secondLineCheck = (point.getX() > secondStartingX && point.getX() < secondEndingX)
                && (point.getY() > secondStartingY && point.getY() < secondEndingY);
        return firstLineCheck || secondLineCheck;
    }

    /**
     * checking inclusion between this line and other line if both slopes are 0.
     *
     * @param point check if the point is on the line.
     * @param other the other line we want to check on him.
     * @return true if one line concludes another.
     */
    public boolean checkInclusionInSlope0(Point point, Line other) {
        double startingX = Math.min(this.start.getX(), this.end.getX());
        double startingY = Math.min(this.start.getY(), this.end.getY());
        double endingX = Math.max(this.start.getX(), this.end.getX());
        double endingY = Math.max(this.start.getY(), this.end.getY());
        double secondStartingX = Math.min(other.start.getX(), other.end.getX());
        double secondStartingY = Math.min(other.start.getY(), other.end.getY());
        double secondEndingX = Math.max(other.start.getX(), other.end.getX());
        double secondEndingY = Math.max(other.start.getY(), other.end.getY());
        boolean firstLineCheck = (point.getX() > startingX && point.getX() < endingX)
                && (point.getY() >= startingY && point.getY() <= endingY);
        boolean secondLineCheck = (point.getX() > secondStartingX && point.getX() < secondEndingX)
                && (point.getY() >= secondStartingY && point.getY() <= secondEndingY);
        return firstLineCheck || secondLineCheck;
    }

    /**
     * checking inclusion between this line and other line if both slopes are infinity.
     *
     * @param point check if the point is on the line.
     * @param other the other line we want to check on him.
     * @return true if one line concludes another.
     */
    public boolean checkInclusionInInfinitySlope(Point point, Line other) {
        double startingX = Math.min(this.start.getX(), this.end.getX());
        double startingY = Math.min(this.start.getY(), this.end.getY());
        double endingX = Math.max(this.start.getX(), this.end.getX());
        double endingY = Math.max(this.start.getY(), this.end.getY());
        double secondStartingX = Math.min(other.start.getX(), other.end.getX());
        double secondStartingY = Math.min(other.start.getY(), other.end.getY());
        double secondEndingX = Math.max(other.start.getX(), other.end.getX());
        double secondEndingY = Math.max(other.start.getY(), other.end.getY());
        boolean firstLineCheck = (point.getX() >= startingX && point.getX() <= endingX)
                && (point.getY() > startingY && point.getY() < endingY);
        boolean secondLineCheck = (point.getX() >= secondStartingX && point.getX() <= secondEndingX)
                && (point.getY() > secondStartingY && point.getY() < secondEndingY);
        return firstLineCheck || secondLineCheck;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line we check the intersection with this line.
     * @return true if the lines intersect, false otherwise.
     */
    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        double m1 = slopLine();
        double m2 = other.slopLine();
        double b1 = findYIntercept();
        double b2 = other.findYIntercept();
        //if one of them has an infinite slope
        if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
            double newY = m2 * this.start.getX() + b2;
            return checkPoint(new Point(this.start.getX(), newY), other);
        }
        if (this.start.getX() != this.end.getX() && other.start.getX() == other.end.getX()) {
            double newY = m1 * other.start.getX() + b1;
            return checkPoint(new Point(other.start.getX(), newY), other);
        }
        //if both with infinite slope
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
            return (checkPoint(new Point(this.start.getX(), this.start.getY()), other))
                    || checkPoint(new Point(this.end.getX(), this.end.getY()), other);
        }
        if (checkSimilarity(m1, m2)) {
            if (!checkSimilarity(b1, b2)) {
                return false;
            }

            return checkPoint(new Point(this.start.getX(), this.start.getY()), other)
                    || checkPoint(new Point(this.end.getX(), this.end.getY()), other);

            //return checkSimilarity(b1, b2);
        } else {
            double counter = b1 - b2;
            double denominator = m2 - m1;
            double newX = counter / denominator;
            double newY = m1 * newX + b1;
            return checkPoint(new Point(newX, newY), other);

        }
    }

    /**
     * Returns true if this 2 lines intersect with this line, false otherwise.
     *
     * @param other1 the first line we want to check if it is intersected with this line.
     * @param other2 the second line we want to check if it is intersected with this line.
     * @return true if this 2 lines intersect with this line, false otherwise.
     */
    // Returns true if this 2 lines intersect with this line, false otherwise
    //we use that method in ass2
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    /**
     * check if the given line is actually a point.
     * @return true if it is point.
     */
    boolean isPoint() {
        return this.start.equals(this.end);
    }

    /**
     * if the lines are intersected, we calculate the intersection point and returns it or if not we return null.
     *
     * @param other the other line that we want to check if it is intersects with this line.
     * @return the intersection point if the lines intersect,and null otherwise.
     */
    // Returns the intersection point if the lines intersect,
// and null otherwise.
    public Point intersectionWith(Line other) {
        double b1 = findYIntercept();
        double b2 = other.findYIntercept();
        double m1 = slopLine();
        double m2 = other.slopLine();
        double counter = b2 - b1;
        double denominator = m1 - m2;
        double newX = counter / denominator;
        double newY = m1 * newX + b1;
        if (isIntersecting(other)) {
            //if both with infinite loop
            if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
                if (this.equals(other)) {
                    if (this.isPoint()) {
                        return new Point(this.start.getX(), this.start.getY());
                    }
                    return null;
                }
                if (checkInclusionInInfinitySlope(new Point(this.start.getX(), this.start.getY()), other)
                        || checkInclusionInInfinitySlope(new Point(this.end.getX(), this.end.getY()), other)
                        || checkInclusionInInfinitySlope(new Point(other.end.getX(), other.end.getY()), this)
                        || checkInclusionInInfinitySlope(new Point(other.start.getX(), other.start.getY()), this)) {
                    return null;
                } else if (checkPoint(new Point(this.start.getX(), this.start.getY()), other)) {
                    return new Point(this.start.getX(), this.start.getY());
                } else if (checkPoint(new Point(this.end.getX(), this.end.getY()), other)) {
                    return new Point(this.end.getX(), this.end.getY());
                }
            }
            if (checkSimilarity(m1, m2)) {
                if ((this.equals(other)) || (!checkSimilarity(b1, b2))) {
                    return null;
                }
                //in case slope is 0 we want to check inclusion
                if (m1 == 0 && m2 == 0) {
                    if (checkInclusionInSlope0(new Point(this.start.getX(), this.start.getY()), other)
                            || checkInclusionInSlope0(new Point(this.end.getX(), this.end.getY()), other)
                            || checkInclusionInSlope0(new Point(other.end.getX(), other.end.getY()), this)
                            || checkInclusionInSlope0(new Point(other.start.getX(), other.start.getY()), this)) {
                        return null;
                    }
                }
                //if both lines with infinity slope


                if (checkInclusion(new Point(this.start.getX(), this.start.getY()), other)
                        || checkInclusion(new Point(this.end.getX(), this.end.getY()), other)
                        || checkInclusion(new Point(other.end.getX(), other.end.getY()), this)
                        || checkInclusion(new Point(other.start.getX(), other.start.getY()), this)) {
                    return null;
                } else if (checkPoint(new Point(this.start.getX(), this.start.getY()), other)) {
                    return new Point(this.start.getX(), this.start.getY());
                } else if (checkPoint(new Point(this.end.getX(), this.end.getY()), other)) {
                    return new Point(this.end.getX(), this.end.getY());
                }
            }
            if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
                double y = m2 * this.start.getX() + b2;
                return new Point(this.start.getX(), y);
            }
            if (this.start.getX() != this.end.getX() && other.start.getX() == other.end.getX()) {
                double y = m1 * other.start.getX() + b1;
                return new Point(other.start.getX(), y);
            }
        }
        if (checkPoint(new Point(newX, newY), other)) {
            return new Point(newX, newY);
        } else {
            return null;
        }
    }

    /**
     * check if 2 lines are equal.
     *
     * @param other the other line we want to compare to this line.
     * @return true is the lines are equal, false otherwise.
     */
    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        Point p1 = other.start;
        Point p2 = other.end;
        return (start.equals(p1) && end.equals(p2) || start.equals(p2) && end.equals(p1));
    }
    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect the rectangle we check intersection points on him.
     * @return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        //if the list is empty
        boolean emptyList = intersectionPoints.isEmpty();
        if (emptyList) {
            return null;
        }
        double minDistance = Double.MAX_VALUE;
        Point closestPointToIntersection = null;
        for (Point p : intersectionPoints) {
            //checking the minimal distance between the start of the line to the intersection point p
            boolean checkClosestDistance = this.start.distance(p) < minDistance;
            if (checkClosestDistance) {
                minDistance = this.start.distance(p);
                //initialize the closest point
                closestPointToIntersection = p;
            }
        }
        return closestPointToIntersection;
    }
}
