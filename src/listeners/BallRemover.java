// 211767561 Ofri Keidar
package listeners;

import game.Counter;
import game.Game;
import sprite.Ball;
import sprite.Block;

/**
 * We create a HitListener called BallRemover that will be in charge of removing balls,
 * and updating an availabe-balls counter.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * constructor for the class fields.
     * @param game the game we want to remove the ball from.
     * @param remainingBalls the count of the remaining balls that in the game.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * when there is hit with the death region block, we remove the ball.
     * @param deathRegion the block that hit him is removing the ball from the game.
     * @param hitter the ball that hit the death block.
     */
    @Override
    public void hitEvent(Block deathRegion, Ball hitter) {
        int removeOneBall = 1;
        hitter.removeFromGame(game);
        remainingBalls.decrease(removeOneBall);
    }
}
