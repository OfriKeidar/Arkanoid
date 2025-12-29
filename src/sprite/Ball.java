// 211767561 Ofri Keidar
package sprite;

import biuoop.DrawSurface;
import game.Game;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;

import java.awt.Color;

/**
 * a class representing a ball.
 */
public class Ball implements Sprite {
    private Point center;
    private final int radius;
    private java.awt.Color color;
    private Velocity vel;
    private final GameEnvironment environment;

    /**
     * the first constructor of the ball.
     *
     * @param center      the center point of the ball.
     * @param r           the size of the ball.
     * @param color       the color of the ball.
     * @param environment the game environment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.color = color;
        this.radius = r;
        this.environment = environment;
    }

    /**
     * the second constructor of the ball.
     *
     * @param x           the x coordinate of the center point of the ball.
     * @param y           the y coordinate of the center point of the ball.
     * @param r           the size of the ball.
     * @param color       the color of the ball.
     * @param environment the game environment of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = new Point(x, y);
        this.color = color;
        this.radius = r;
        this.environment = environment;
    }

    /**
     * a getter for the x-coordinate of the center point.
     *
     * @return the x-coordinate of the center point.
     */
    // accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * a getter for the y-coordinate of the center point.
     *
     * @return the y-coordinate of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * a getter for the size of the ball.
     *
     * @return the size of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * a getter for the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * setting the ball color.
     * @param color the new color of the ball.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * in this method we draw the ball on the given DrawSurface.
     *
     * @param surface the surface we want to use and draw on it.
     */
    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(this.getX(), this.getY(), getSize());
    }

    /**
     * in each call of this method we are moving one step.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * method that we add the ball to the game.
     *
     * @param game the game environment we want to add the ball to.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
    /**
     * method that we remove the ball from the game.
     *
     * @param g the game environment we want to remove the ball from.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

    /**
     * setting the velocity of the ball.
     *
     * @param v the velocity we get to initialize vel field.
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * second way to set the velocity of the ball,but now by dx,dy fields.
     *
     * @param dx the x coordinate.
     * @param dy the y coordinate.
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * a getter for the ball velocity.
     *
     * @return this ball velocity.
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * calculating and changing the position of the point and updating the location
     * and the velocity.
     */
    public void moveOneStep() {
        int guiWidth = 800;
        int guiHeight = 600;
        Point startingTrajectoryPoint = this.center;
        Point endingTrajectoryPoint = getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(startingTrajectoryPoint, endingTrajectoryPoint);
        this.center = getVelocity().applyToPoint(this.center);
        //check on gui borders
        if (this.center.getX() - this.radius <= 0 || this.center.getX() + this.radius >= guiWidth) {
            setVelocity(-this.vel.getDx(), this.vel.getDy());
            //this.vel = new Velocity(-this.vel.getDx(), this.vel.getDy());
        }

        if (this.center.getY() - this.radius <= 0 || this.center.getY() + this.radius >= guiHeight) {
            setVelocity(this.vel.getDx(), -this.vel.getDy());
            //this.vel = new Velocity(this.vel.getDx(), -this.vel.getDy());
        }
        // Check if a collision occurred
        if (this.environment.getClosestCollision(trajectory) != null) {
            Point collisionPoint = this.environment.getClosestCollision(trajectory).getCollisionPoint();
            Collidable collidable = this.environment.getClosestCollision(trajectory).getCollisionObject();
            this.vel = collidable.hit(this, collisionPoint, this.vel);
            this.center = collidable.determinesHit(collisionPoint);
        }
    }
}

