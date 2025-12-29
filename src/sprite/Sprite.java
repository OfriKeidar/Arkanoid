// 211767561 Ofri Keidar
package sprite;
import biuoop.DrawSurface;

/**
 * In computer graphics and games, a Sprite is a game object that can be drawn to the screen.
 */
public interface Sprite {
    /**
     * in this method we draw the sprite to the screen.
     * @param d the surface we want to draw on.
     */
    void drawOn(DrawSurface d);
    /**
     * in this method we notify the sprite that time has passed.
     */
    void timePassed();
}

