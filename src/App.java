import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        if(GameManager.gameState == GameState.AWAIT){
            GameManager.Start();
        }
        if(GameManager.gameState == GameState.STARTED){       
            GameManager.Initialize(2);
        }
    }
}
