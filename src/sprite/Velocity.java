// 211767561 Ofri Keidar
// Velocity specifies the change in position on the `x` and the `y` axes.
package sprite;
import geometry.Point;
/**
 * class representing the ball velocity.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    // constructor

    /**
     * constructor to the Velocity fields.
     *
     * @param dx the change in the x-asis.
     * @param dy the change in the y-asis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * calculating velocity by getting angle and speed.
     *
     * @param angle the angle of impact on the surface.
     * @param speed the ball speed.
     * @return the ball velocity by math calculations.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = -speed * Math.cos(radians);
        return new Velocity(dx, dy);
    }

    /**
     * Calculates the speed based on the change in x (dx) and change in y (dy) components of velocity.
     *
     * @return the speed magnitude of the velocity
     */
    public double getSpeed() {
        double dxInPower = this.dx * this.dx;
        double dyInPower = this.dy * this.dy;
        return Math.sqrt(dxInPower + dyInPower);
    }

    /**
     * method that handles edge cade when the angle is negative.
     *
     * @param dx the change in the x-asis.
     * @param dy the change in the y-asis.
     * @return the fixed angel.
     */
    //in this method we use in ass2
    public static double getAngle(double dx, double dy) {
        double angle = Math.toDegrees(Math.atan2(dy, dx));
        if (angle < 0) {
            return angle + 360;
        } else {
            return angle;
        }
    }

    /**
     * getter for the dx field.
     *
     * @return this dx field.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getter for the dy field.
     *
     * @return this dy field.
     */
    public double getDy() {
        return this.dy;
    }

    // Take a point with position (x,y) and return a new point
// with position (x+dx, y+dy)

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p the starting point before the update.
     * @return the updated point.
     */
    public Point applyToPoint(Point p) {
        if (p == null) {
            return null;
        } else {
            return new Point(p.getX() + this.dx, p.getY() + this.dy);
        }
    }
}
