// 211767561 Ofri Keidar
package sprite;
import geometry.Point;
/**
 * class that has the information about the points at which the collision occurs
 * and the collidable objects involved in the collision.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * constructor for the collision info fields.
     * @param collisionP the collision point.
     * @param collisionO the collision object.
     */
    public CollisionInfo(Point collisionP, Collidable collisionO) {
        this.collisionPoint = collisionP;
        this.collisionObject = collisionO;
    }

    /**
     * in this method we return the point at which the collision occurs.
     * @return the point at which the collision occurs.
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }
    /**
     * in this method we return the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable getCollisionObject() {
        return this.collisionObject;
    }
}
