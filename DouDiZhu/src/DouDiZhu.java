import java.util.ArrayList;
import java.util.Scanner;
//main class
public class DouDiZhu {
    public static void main(String[] args) {
        Game game = new Game();
        game.deal();
        game.callForLord();
        game.playMain();
    }
}
