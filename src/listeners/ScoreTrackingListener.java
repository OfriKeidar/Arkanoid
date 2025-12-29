// 211767561 Ofri Keidar
package listeners;

import game.Counter;
import sprite.Ball;
import sprite.Block;

/**
 * We will implement a HitListener called ScoreTrackingListener to update this counter
 * when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * constructor for the current score.
     * @param scoreCounter the counter of the current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * in every legal hit we are adding to the score 5 points.
     * @param beingHit This parameter is the Block that's being hit.
     * @param hitter The hitter parameter is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        int hittingBlockPoints = 5;
        this.currentScore.increase(hittingBlockPoints);
    }
}
