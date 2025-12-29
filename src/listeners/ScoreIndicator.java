// 211767561 Ofri Keidar
package listeners;

import biuoop.DrawSurface;
import game.Counter;
import game.Game;
import sprite.Sprite;

import java.awt.Color;

/**
 *  the score indicator will be in charge of displaying the current score.
 *  The ScoreIndicator will hold a reference to the scores counter,
 *  and will be added to the game as a sprite positioned at the top of the screen.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * constructor for the score field.
     * @param s the counter of the score.
     */
    public ScoreIndicator(Counter s) {
        this.score = s;
    }

    /**
     * drawing the score block with text.
     * @param d the surface we want to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(350, 15, "Score: " + score.getValue(), 18);
    }

    /**
     * adding the score indicator to the game.
     * @param g the game we add the sprite to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * in that method we need to do nothing since it doesn't need to perform any actions in response to time passing.
     */
    @Override
    public void timePassed() {
    }
}
