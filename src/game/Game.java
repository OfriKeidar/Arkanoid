// 211767561 Ofri Keidar
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreIndicator;
import listeners.ScoreTrackingListener;
//import sprite.*;
import sprite.Ball;
import sprite.Collidable;
import sprite.Sprite;
import sprite.SpriteCollection;
import sprite.Block;
import sprite.Paddle;
import sprite.Velocity;

import java.awt.Color;
import java.util.Random;

/**
 * in this class we are making the game and wet the initialize and the run of the game.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private final GUI gui;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final Counter score;

//    /**
//     * constructor to the game fields.
//     *
//     * @param sprites     the game objects that can be drawn to the screen.
//     * @param environment collection of such things a ball can collide with.
//     */
//    public Game(SpriteCollection sprites, GameEnvironment environment) {
//        this.sprites = sprites;
//        this.environment = environment;
//        int guiWidth = 800;
//        int guiHeight = 600;
//        String gameName = "The Arkanoid Game";
//        this.gui = new GUI(gameName, guiWidth, guiHeight);
//    }

    /**
     * second constructor to the game fields.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        int guiWidth = 800;
        int guiHeight = 600;
        String gameName = "The Arkanoid Game";
        this.gui = new GUI(gameName, guiWidth, guiHeight);
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = new Counter();
    }

    /**
     * in this method we add the 'c' collidable to the game environment.
     *
     * @param c the collidable we add to the game environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * in this method we remove the 'c' collidable from the game environment.
     *
     * @param c the collidable we remove from the game environment.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * in this method we add the 's' sprite to the sprites list.
     *
     * @param s the sprite we want to add to the sprites list.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * in this method we remove the 's' sprite from the sprites list.
     *
     * @param s the sprite we want to remove from the sprites list.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks the Ball and the Paddle and add them to the game.
     */
    public void initialize() {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        addingBallsToTheGame();
        addingDeathRegionToTheGame();
        addingPaddleToTheGame();
        addingBlocksToTheGame();
        addingBordersToTheGame();
        addingScoreIndicatorToTheGame();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int start = 0;
        int width = 800;
        int height = 600;
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.lightGray);
            d.fillRectangle(start, start, width, height);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (remainingBlocks.getValue() == 0) {
                score.increase(100); // Bonus points for clearing all blocks
            }
            if (this.remainingBlocks.getValue() == 0 || this.remainingBalls.getValue() == 0) {
                gui.close();
            }
        }
    }

    /**
     * method that initialize the balls velocity and add them to the game.
     */
    public void addingBallsToTheGame() {
        Random rand = new Random();
        int radius = 5;
        int addOneBall = 1;
        int numBalls = 3;
        for (int i = 0; i < numBalls; i++) {
            Color color;
            if (i == 1) {
                color = Color.BLUE;
            } else if (i == 2) {
                color = Color.GREEN;
            } else {
                color = Color.yellow;
            }
            Ball ball = new Ball(new Point(100, 200), radius, color, this.environment);
            ball.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), 5));
            ball.addToGame(this);
            this.remainingBalls.increase(addOneBall);
        }
    }

    /**
     * method that initialize each layer with blocks in different color and size for every layer.
     */
    public void addingBlocksToTheGame() {
        //PrintingHitListener printingListener = new PrintingHitListener();
        int width = 800;
        int rectangleWidth = 40;
        int rectangleHeight = 25;
        int numLayers = 6;
        int addOneBlock = 1;
        for (int i = 1; i <= numLayers; i++) {
            Color color = Game.randomColor();
            for (int j = 0; j < 13 - i; j++) {
                Point blockPoint = new Point(width - 50 - j * 40, 85 + i * 25);
                Rectangle blockRectangle = new Rectangle(blockPoint, rectangleWidth, rectangleHeight);
                Block block = new Block(blockRectangle, color);
                //block.addHitListener(printingListener);
                remainingBlocks.increase(addOneBlock);
                block.addHitListener(new BlockRemover(this, remainingBlocks));
                block.addHitListener(new ScoreTrackingListener(score));
                block.addToGame(this);
            }
        }
    }

    /**
     * method that add the borders to the game.
     */
    public void addingBordersToTheGame() {
        int start = 0;
        int width = 800;
        int height = 600;
        int size = 10;
        Color borderColor = Color.darkGray;
        Point leftBorderPoint = new Point(start, start);
        Point rightBorderPoint = new Point(width - size, start);
        Point upBorderPoint = new Point(start, start + 20);
        //Point downBorderPoint = new Point(start, height - size);
        Block left = new Block(new Rectangle(leftBorderPoint, size, height), borderColor);
        Block right = new Block(new Rectangle(rightBorderPoint, size, height), borderColor);
        Block up = new Block(new Rectangle(upBorderPoint, width, size), borderColor);
        //Block down = new Block(new Rectangle(downBorderPoint, width, size), borderColor);
        left.addToGame(this);
        up.addToGame(this);
        right.addToGame(this);
        //down.addToGame(this);
    }

    /**
     * method to make the paddle and add him to the game.
     */
    public void addingPaddleToTheGame() {
        int xPaddle = 350;
        int yPaddle = 565;
        int paddleWidth = 100;
        int paddleHeight = 20;
        int paddleSpeed = 10;
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Point paddlePoint = new Point(xPaddle, yPaddle);
        Rectangle paddleRect = new Rectangle(paddlePoint, paddleWidth, paddleHeight);
        Paddle paddle = new Paddle(keyboard, paddleRect, paddleSpeed, Color.white.darker().darker());
        paddle.addToGame(this);
    }

    /**
     * in that method we create the death region block and adding him to the game.
     */
    public void addingDeathRegionToTheGame() {
        int start = 0;
        int width = 800;
        int height = 600;
        int size = 10;
        Rectangle deathRegionRec = new Rectangle(new Point(start, height - 5), width, size);
        Block deathRegion = new Block(deathRegionRec, Color.LIGHT_GRAY);
        deathRegion.addHitListener(new BallRemover(this, this.remainingBalls));
        deathRegion.addToGame(this);
    }

    /**
     * in that method we add the score to the game.
     */
    public void addingScoreIndicatorToTheGame() {
        ScoreIndicator scoreI = new ScoreIndicator(score);
        scoreI.addToGame(this);
    }

    /**
     * method to choose random color.
     *
     * @return the random selected color.
     */
    public static Color randomColor() {
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return new Color(r, g, b);
    }

    /**
     * method to running the game.
     */
    public static void runTheGame() {
        Game game = new Game();
        game.initialize();
        game.run();
    }

    /**
     * the main we actually run the game from.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        runTheGame();
    }
}

