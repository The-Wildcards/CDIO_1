import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum GameState { AWAIT, SETUP, PLAYING, ENDED}

public class GameManager {

    public GameState gameState = GameState.AWAIT;

    public List<Player> players = new ArrayList<>();  
    public List<Dice> dices = new ArrayList<>();
    public int turnIndex = 0;
    public int lastRoll = 0;

    public void Start(){

        // Create a welcome message using the StringBuilder
        StringBuilder builder = new StringBuilder();
 
        builder.append(new String("Welcome to the dice game! \n\n"));

        builder.append(new String("(1) Play. \n"));
        builder.append(new String("(2) Rules. \n"));
        builder.append(new String("(3) Exit. \n"));

        System.out.println(builder.toString());

        // Create a scanner to read the next input
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextInt()){
            var input = scanner.nextInt();
            switch(input){
                case 1 -> {
                    gameState = GameState.SETUP;
                    return;
                }
                case 2 -> {
                    // Display rules
                }
                case 3 -> {
                    System.out.println("Thanks for playing!");
                    // Exit the terminal
                }
                default -> {
                    System.out.println("Please specify a valid number");
                }
            }
        }

        scanner.close();
    }


    public void Initialize(int players){
        // Create the amount of players for the game
        CreatePlayers(players);

        // Setup the dice
        CreateDice(2);   
    }

    private void CreatePlayers(int amount){
        // Create a scanner to read the next input
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < amount; i++){
            // Display a command message.
            System.out.println("Insert a name for Player " + (i + 1));

            // Set the name.
            String name = scanner.nextLine();

            // Create a new player with the specified name.
            Player player = new Player(name, 0);

            // Add the player to the list of players.
            if(!players.contains(player)){
                players.add(player);
            }
        }

        // We close the scanner in the ExpansionManager which follows.
    }

    private void CreateDice(int amount){

        // Create dices specified by the amount
        for(int i = 0; i < amount; i++){
            Dice dice = new Dice(0);

            // Add the dice to the list of dices.
            if(!dices.contains(dice)){
                dices.add(dice);
            }
        }
    }
    


    public void OnDiceRoll(){
        Player playerTurn = players.get(turnIndex);

        int diceSum = 0;
        for (Dice dice : dices) {
            diceSum += dice.getRollValue();
        }

        System.out.println(playerTurn.name + " has rolled " + diceSum);
    }
}

