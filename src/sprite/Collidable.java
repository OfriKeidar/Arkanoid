// 211767561 Ofri Keidar
package sprite;
import geometry.Point;
import geometry.Rectangle;

/**
 * The Collidable interface will be used by things that can be collided with. In this assignment, this means
 * the Blocks and the Paddle.
 */
public interface Collidable {
    /**
     * method to return the collision shape of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * we calculate the velocity in some hit cases on the object.
     * @param hitter notify hit if the hitter ball color is different from the block color.
     * @param collisionPoint the collision point between the ball and the object.
     * @param currentVelocity the velocity before the hit.
     * @return the updated velocity after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * in this method we check the hit almost closely before the hit happens.
     * @param collisionPoint the collision point.
     * @return the closest point before the hit happens.
     */
    Point determinesHit(Point collisionPoint);
}
