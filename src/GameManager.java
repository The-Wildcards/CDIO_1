import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum GameState { AWAIT, STARTED, PLAYING, ENDED}

public class GameManager {

    public static GameState gameState = GameState.AWAIT;

    public static List<Player> players = new ArrayList<>();  
    public static List<Dice> dices = new ArrayList<>();

    public static void Start(){
        StringBuilder builder = new StringBuilder();
 
        builder.append(new String("Welcome to the dice game! \n\n"));

        builder.append(new String("(1) Play. \n"));
        builder.append(new String("(2) Rules. \n"));
        builder.append(new String("(3) Exit. \n"));

        String result = builder.toString();

        System.out.println(result);

        // Create a scanner to read the next input
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextInt()){
            var input = scanner.nextInt();
            switch(input){
                case 1 -> {
                    gameState = GameState.STARTED;
                    return;
                }
                case 2 -> {
                    // Displaye rules
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


    public static void Initialize(int players){
        // Create the amount of players for the game
        CreatePlayers(players);

        // Setup the dice
        CreateDice(2);
    
        // Setup any expansion packs
        SetupExpansions();
    }

    public static void CreatePlayers(int amount){
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

        // Close the scanner as to ensure no resource leaks.
        scanner.close();
    }

    public static void CreateDice(int amount){

        // Create dices specified by the amount
        for(int i = 0; i < amount; i++){
            Dice dice = new Dice(0);

            // Add the dice to the list of dices.
            if(!dices.contains(dice)){
                dices.add(dice);
            }
        }
    }

    private static void SetupExpansions(){
        StringBuilder builder = new StringBuilder();
 
        builder.append(new String("Would you like to include any expansions? \n\n"));
        builder.append(new String("(1) Expansion 1: If the player rolls two 1, the player looses all their points.\n"));
        builder.append(new String("(2) Expansion 2: If the player rolls two equals, the player gets an extra turn.\n"));
        builder.append(new String("(3) Expansion 3: If the player in his previous throw, rolled two 6, and rolles two 6 in this roll, he wins the game.\n"));
        builder.append(new String("(4) Expansion 4: After reaching 40 points, the player has to roll two equals, to win the game.\n"));
        builder.append(new String("(5) All of the above \n"));
        builder.append(new String("(6) None \n"));

        String result = builder.toString();

        System.out.println(result);

        // Create a scanner to read the next input
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextInt()){
            var input = scanner.nextInt();
            switch(input){
                case 1 -> {
                    // Enable expansion 1
                }
                case 2 -> {
                     // Enable expansion 2
                }
                case 3 -> {
                     // Enable expansion 3
                }
                case 4 -> {
                     // Enable expansion 4
                }
                case 5 -> {
                     // Enable all expensions
                }
                case 6 -> {
                    // Continue without any expansions
                }
                default -> {
                    System.out.println("Please specify a valid number");
                }
            }
        }
        

        scanner.close();
    }
}
