public class App {
    public static GameManager gameManager = new GameManager();
    public static ExpansionManager expansionManager = new ExpansionManager();

    public static void main(String[] args) throws Exception {

        if(gameManager.gameState == GameState.AWAIT){
            gameManager.Start();
        }
        if(gameManager.gameState == GameState.SETUP){       
            gameManager.Initialize(2);
            expansionManager.GenerateExpansions();
            expansionManager.SetupExpansions();
        }
        if(gameManager.gameState == GameState.PLAYING){
            gameManager.OnDiceRoll();
        }
    }
}
