// 211767561 Ofri Keidar
package game;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprite.Collidable;
import sprite.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * class that representing collection of things that ball can collide with.
 */
public class GameEnvironment {
    private final List<Collidable> obstacles;

    /**
     * constructor for the game environment fields.
     *
     * @param obstacles the list of the obstacles.
     */
    public GameEnvironment(List<Collidable> obstacles) {
        this.obstacles = new ArrayList<>();
        this.obstacles.addAll(obstacles);
    }

    /**
     * second option to the constructor.
     */
    public GameEnvironment() {
        this.obstacles = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the collidable we want to add to the list.
     */
    public void addCollidable(Collidable c) {
        this.obstacles.add(c);
    }
    /**
     * remove the given collidable from the environment.
     *
     * @param c the collidable we want to remove from the list.
     */
    public void removeCollidable(Collidable c) {
        this.obstacles.remove(c);
    }

    // Assume an object moving from line.start() to line.end().

    /**
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the line we assume the object moving on.
     * @return the collision info in the given line.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double minDistance = Double.MAX_VALUE;
        Point closestCollision = null;
        Collidable closestCollisionObject = null;
        for (Collidable c : obstacles) {
            Rectangle getRect = c.getCollisionRectangle();
            Point intersectionPoint = trajectory.closestIntersectionToStartOfLine(getRect);
            if (intersectionPoint != null) {
                Point startingLine = trajectory.start();
                boolean checkClosestDistance = minDistance > startingLine.distance(intersectionPoint);
                if (checkClosestDistance) {
                    minDistance = startingLine.distance(intersectionPoint);
                    closestCollision = intersectionPoint;
                    closestCollisionObject = c;
                }
            }
        }
        boolean closestCollisionNotNull = (closestCollision != null);
        if (closestCollisionNotNull) {
            return new CollisionInfo(closestCollision, closestCollisionObject);
        } else {
            return null;
        }
    }
}

