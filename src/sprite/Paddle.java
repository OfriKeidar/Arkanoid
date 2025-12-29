// 211767561 Ofri Keidar
package sprite;
import game.Game;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The Paddle is the player in the game. It is a rectangle that is controlled by the arrow keys, and moves
 * according to the player key presses. It should implement the Sprite and the Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private final KeyboardSensor keyboard;
    private Rectangle paddleShape;
    private final int speed;
    private final Color color;

    /**
     * constructor to the paddle fields.
     *
     * @param keyboard the paddle controlled by the keyboard.
     * @param rectangle the paddle shape.
     * @param speed    the speed of the paddle.
     * @param color    the color of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, int speed, Color color) {
        this.keyboard = keyboard;
        this.paddleShape = rectangle;
        this.speed = speed;
        this.color = color;
    }

    /**
     * in this method we are moving left, and we take into account the fact the paddle need to move in circular route.
     */
    public void moveLeft() {
        int leftBorderX = 0;
        int rightBorderX = 800;
        double paddleX = paddleShape.getUpperLeft().getX() - speed;
        double paddleY = paddleShape.getUpperLeft().getY();
        double paddleWidth = paddleShape.getWidth();
        double paddleHeight = paddleShape.getHeight();
        //we want circular fashion
        if (paddleX < leftBorderX) {
            paddleX = rightBorderX;
        }
        Point paddlePoint = new Point(paddleX, paddleY);
        paddleShape = new Rectangle(paddlePoint, paddleWidth, paddleHeight);
    }

    /**
     * in this method we are moving right, and we take into account the fact the paddle need to move in circular route.
     */
    public void moveRight() {
        int leftBorderX = 0;
        int rightBorderX = 800;
        double paddleX = paddleShape.getUpperLeft().getX() + speed;
        double paddleY = paddleShape.getUpperLeft().getY();
        double paddleWidth = paddleShape.getWidth();
        double paddleHeight = paddleShape.getHeight();
        //we want circular fashion
        if (paddleX + paddleShape.getWidth() > rightBorderX) {
            paddleX = leftBorderX;
        }
        Point paddlePoint = new Point(paddleX, paddleY);
        paddleShape = new Rectangle(paddlePoint, paddleWidth, paddleHeight);
    }

    /**
     * if the key arrow is LEFT we are moving left, and if the key arrow is RIGHT we are moving right.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * method to draw the paddle on the d draw surface.
     *
     * @param d the surface we want to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        int paddleX = (int) paddleShape.getUpperLeft().getX();
        int paddleY = (int) paddleShape.getUpperLeft().getY();
        int paddleWidth = (int) paddleShape.getWidth();
        int paddleHeight = (int) paddleShape.getHeight();
        d.fillRectangle(paddleX, paddleY, paddleWidth, paddleHeight);
    }

    /**
     * returning the collision rectangle.
     *
     * @return the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return paddleShape;
    }

    /**
     * calculate the new velocity after the ball hit in the paddle by dividing the paddle to 5 regions,
     * and each region returns different angel.
     *
     * @param collisionPoint the collision point between the ball and the object.
     * @param currentVelocity the velocity before the hit.
     * @return the updated velocity after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        final int numPaddleRegions = 5;
        final int leftMostPaddleRegion = 1;
        final int leftPaddleRegion = 2;
        final int centralPaddleRegion = 3;
        final int rightPaddleRegion = 4;
        final int region1Speed = 300;
        final int region2Speed = 330;
        final int region3Speed = 360;
        final int region4Speed = 30;
        final int region5Speed = 60;
        double paddleWidth = paddleShape.getWidth();
        double paddleRegionWidth = paddleWidth / numPaddleRegions;
        double paddleX = paddleShape.getUpperLeft().getX();
        double xHittingPoint = collisionPoint.getX() - paddleX;
        double speed = currentVelocity.getSpeed();
        boolean hittingRegion1 = xHittingPoint < leftMostPaddleRegion * paddleRegionWidth;
        boolean hittingRegion2 = xHittingPoint < leftPaddleRegion * paddleRegionWidth;
        boolean hittingRegion3 = xHittingPoint < centralPaddleRegion * paddleRegionWidth;
        boolean hittingRegion4 = xHittingPoint < rightPaddleRegion * paddleRegionWidth;
        Velocity newVelocity;

        if (hittingRegion1) {
            newVelocity = Velocity.fromAngleAndSpeed(region1Speed, speed);
        } else if (hittingRegion2) {
            newVelocity = Velocity.fromAngleAndSpeed(region2Speed, speed);
        } else if (hittingRegion3) {
            newVelocity = Velocity.fromAngleAndSpeed(region3Speed, speed);
            //new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (hittingRegion4) {
            newVelocity = Velocity.fromAngleAndSpeed(region4Speed, speed);
        } else {
            //if we in rightMostPaddleRegion-hitting region 5.
            newVelocity = Velocity.fromAngleAndSpeed(region5Speed, speed);
        }
        return newVelocity;
    }

    /**
     * returns the closest point to hit the paddle.
     * @param collisionPoint the collision point.
     * @return the updated point if we need to update.
     */
    @Override
    public Point determinesHit(Point collisionPoint) {
        final double epsilonSimilarity = 0.001;
        final int minDistance = 2;
        double paddleX = paddleShape.getUpperLeft().getX();
        double paddleY = paddleShape.getUpperLeft().getY();
        double paddleWidth = paddleShape.getWidth();
        double paddleHeight = paddleShape.getHeight();

        if (Math.abs(collisionPoint.getX() - paddleX) < epsilonSimilarity) {
            return new Point(collisionPoint.getX() - minDistance, collisionPoint.getY());
        } else if (Math.abs(collisionPoint.getY() - paddleY) < epsilonSimilarity) {
            return new Point(collisionPoint.getX(), collisionPoint.getY() - minDistance);
        } else if (Math.abs(collisionPoint.getX() - (paddleX + paddleWidth)) < epsilonSimilarity) {
            return new Point(collisionPoint.getX() + minDistance, collisionPoint.getY());
        } else if (Math.abs(collisionPoint.getY() - (paddleY + paddleHeight)) < epsilonSimilarity) {
            return new Point(collisionPoint.getX(), collisionPoint.getY() + minDistance);
        }
        return collisionPoint;
    }

    /**
     * adding the paddle to the game.
     * @param g the game we want to add the paddle to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
