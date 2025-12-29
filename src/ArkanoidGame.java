// 211767561 Ofri Keidar
import game.Game;
/**
 * running the game class.
 */
//ass5
public class ArkanoidGame {
    /**
     * method to running the game.
     */
    public static void runTheGame() {
        Game game = new Game();
        game.initialize();
        game.run();
    }
    /**
     * we are running the game from the main method.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        runTheGame();
    }
}
