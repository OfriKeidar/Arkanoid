// 211767561 Ofri Keidar
package sprite;

import biuoop.DrawSurface;
import game.Game;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class representing a block, and doing calculates and things that we need to do if we hit a block.
 * then drawing him and add to the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;
    private final Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor for the block fields.
     *
     * @param rect  the block is a rectangle.
     * @param color the color of thr block.
     */
    public Block(Rectangle rect, Color color) {
        this.rectangle = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * second constructor to thr block fields, but now we are 'building' the rectangle.
     *
     * @param p      the left upper point of the block.
     * @param width  the width of the block.
     * @param height the height of the block.
     * @param color  the color of the block.
     */
    public Block(Point p, double width, double height, Color color) {
        this.rectangle = new Rectangle(p, width, height);
        this.color = color;
    }

    /**
     * method that returns the collision rectangle.
     *
     * @return the collision rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * we calculate the velocity in some hit cases on the block.
     * @param hitter notify hit if the hitter ball color is different from the block color.
     * @param collisionPoint  the collision point between the ball and the block.
     * @param currentVelocity the velocity before the hit.
     * @return the updated velocity after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double epsilonSimilarity = 0.001;
        double currentVelocityDx = currentVelocity.getDx();
        double currentVelocityDy = currentVelocity.getDy();
        double leftRectX = this.rectangle.getUpperLeft().getX();
        double leftRectY = this.rectangle.getUpperLeft().getY();
        double rightRectX = this.rectangle.getDownerRight().getX();
        double rightRectY = this.rectangle.getDownerRight().getY();
        double xCollision = collisionPoint.getX();
        double yCollision = collisionPoint.getY();
        if (Math.abs(xCollision - leftRectX) < epsilonSimilarity
                ||
                Math.abs(xCollision - rightRectX) < epsilonSimilarity) {
            // when there is horizontal hit
            currentVelocityDx = -currentVelocityDx;
        }
        if (Math.abs(yCollision - leftRectY) < epsilonSimilarity
                ||
                Math.abs(yCollision - rightRectY) < epsilonSimilarity) {
            // when there is vertical hit
            currentVelocityDy = -currentVelocityDy;
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return new Velocity(currentVelocityDx, currentVelocityDy);
    }

    /**
     * in this method we check the hit almost closely before the hit happens.
     *
     * @param collision the collision point.
     * @return the closest point before the hit happens.
     */
    public Point determinesHit(Point collision) {
        double epsilonSimilarity = 0.001;
        double xCollision = collision.getX();
        double yCollision = collision.getY();
        int minDistance = 2;
        double leftRectX = this.rectangle.getUpperLeft().getX();
        double leftRectY = this.rectangle.getUpperLeft().getY();
        double rightRectX = this.rectangle.getDownerRight().getX();
        double rightRectY = this.rectangle.getDownerRight().getY();
        boolean checkLeftX = Math.abs(xCollision - leftRectX) < epsilonSimilarity;
        boolean checkLeftY = Math.abs(yCollision - leftRectY) < epsilonSimilarity;
        boolean checkRightX = Math.abs(xCollision - rightRectX) < epsilonSimilarity;
        boolean checkRightY = Math.abs(yCollision - rightRectY) < epsilonSimilarity;
        if (checkLeftX) {
            return new Point(xCollision - minDistance, yCollision);
        }
        if (checkLeftY) {
            return new Point(xCollision, yCollision - minDistance);
        }
        if (checkRightX) {
            return new Point(xCollision + minDistance, yCollision);
        }
        if (checkRightY) {
            return new Point(xCollision, yCollision + minDistance);
        }

        return collision;
    }

    /**
     * draw on method to draw the block.
     *
     * @param surface the surface we draw the block on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        int leftRectX = (int) rectangle.getUpperLeft().getX();
        int leftRectY = (int) rectangle.getUpperLeft().getY();
        int widthRect = (int) (rectangle.getUpperRight().getX() - rectangle.getUpperLeft().getX());
        int heightRect = (int) (rectangle.getDownerRight().getY() - rectangle.getUpperRight().getY());
        surface.drawRectangle(leftRectX, leftRectY, widthRect, heightRect);
        surface.setColor(this.color);
        surface.fillRectangle(leftRectX, leftRectY, widthRect, heightRect);
    }

    /**
     * in that method we need to do nothing :) .
     */
    @Override
    public void timePassed() {
    }

    /**
     * check if the color of the block and the given ball are the same.
     * @param ball check if the color of this ball is same as this block color.
     * @return true if it is thw same color and false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        Color ballColor = ball.getColor();
        return this.color.equals(ballColor);
    }

    /**
     * removing the block from the game.
     *
     * @param game the game we want to remove the block from.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * adding the block to game.
     *
     * @param game the game we want to add the block to.
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * adding to this specific block.
     * @param hl the listener we want to add to the HitListener list.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removing from this specific block.
     * @param hl the listener we want to remove from the HitListener list.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * will be called whenever a hit() occurs,
     * and will notify all the registered HitListener objects by calling their hitEvent method.
     * @param hitter the ball that hitting this block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * getter for the block color.
     * @return the color of the block.
     */
    public Color getColor() {
        return this.color;
    }
}
