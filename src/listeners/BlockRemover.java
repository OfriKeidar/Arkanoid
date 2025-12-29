// 211767561 Ofri Keidar
package listeners;

import game.Counter;
import game.Game;
import sprite.Ball;
import sprite.Block;

import java.awt.Color;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * constructor to the class fields.
     * @param game the game we want to remove the block from.
     * @param remainingBlocks the count of the remaining blocks in the game.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game(if the color of the block and the ball are different).
     * if we remove the block, the ball color change accordingly.
     * @param beingHit the block that being hit.
     * @param hitter the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        boolean blockAndBallDifferentColor = !beingHit.ballColorMatch(hitter);
        int removeOneBlock = 1;
        Color ballNewColor = beingHit.getColor();
        if (blockAndBallDifferentColor) {
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            remainingBlocks.decrease(removeOneBlock);
            hitter.setColor(ballNewColor);
        }
    }

}
