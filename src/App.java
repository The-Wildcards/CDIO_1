import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        if(GameManager.gameState == GameState.AWAIT){
            GameManager.Start();
        }
        if(GameManager.gameState == GameState.SETUP){       
            ExpansionManager.GenerateExpansions();
            GameManager.SetupExpansions();
            GameManager.Initialize(2);
        }
        if(GameManager.gameState == GameState.PLAYING){

        }
    }
}
