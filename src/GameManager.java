import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum GameState { AWAIT, SETUP, PLAYING, ENDED}

public class GameManager {

    public static GameState gameState = GameState.AWAIT;

    public static List<Player> players = new ArrayList<>();  
    public static List<Dice> dices = new ArrayList<>();
    public static int lastRoll = 0;

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


    public static void Initialize(int players){
        // Create the amount of players for the game
        CreatePlayers(players);

        // Setup the dice
        CreateDice(2);   
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

    public static void SetupExpansions() {
        // Loop through until the gamestate has been changed
        while(gameState == GameState.SETUP){
            StringBuilder builder = new StringBuilder();
                
            builder.append(new String("Would you like to include any expansions? \n\n"));

            for(int i = 0; i < ExpansionManager.exspansions.size(); i++){

                // Get the rules of the expansion as string
                String ruleStr = ExpansionManager.exspansions.get(i).GetRulesAsString();

                // Set the ID
                int id = i + 1;

                // Get a color based on whether or not the expansion is activated
                String color = ExpansionManager.exspansions.get(i).enabled ? ColorUtils.TEXT_GREEN : ColorUtils.TEXT_RED;

                builder.append(new String(color + "(" + (id) + ")" + " Expansion " + (id) + ": " + ColorUtils.TEXT_WHITE + ruleStr ));
            }

            builder.append(new String("(5) All of the above \n"));
            builder.append(new String("(6) None \n\n"));

            builder.append(new String("(7) Continue \n"));
            
            System.out.println(builder.toString());

            Scanner scanner = new Scanner(System.in);

            int input = scanner.nextInt();

            switch (input) {
                case 1 -> {
                    // Enable expansion 1
                    Expansion exp = ExpansionManager.GetExpansionByID(1);
                    exp.enabled = !exp.enabled;
                    continue;
                }
                case 2
                 -> {
                    // Enable expansion 2
                    Expansion exp = ExpansionManager.GetExpansionByID(2);
                    exp.enabled = !exp.enabled;
                    continue;  
                }
                case 3 -> {
                    // Enable expansion 3
                    Expansion exp = ExpansionManager.GetExpansionByID(3);
                    exp.enabled = !exp.enabled;
                    continue;
                }
                case 4 -> {
                    // Enable expansion 4
                    Expansion exp = ExpansionManager.GetExpansionByID(4);
                    exp.enabled = !exp.enabled;
                    continue;   
                }
                case 5 -> {
                    // Enable all expansions
                    ExpansionManager.GetExpansionByID(1).enabled = true;         
                    ExpansionManager.GetExpansionByID(2).enabled = true;         
                    ExpansionManager.GetExpansionByID(3).enabled = true;         
                    ExpansionManager.GetExpansionByID(4).enabled = true;  
                    continue;
                }
                case 6 -> {
                    // Enable all expansions
                    ExpansionManager.GetExpansionByID(1).enabled = false;         
                    ExpansionManager.GetExpansionByID(2).enabled = false;         
                    ExpansionManager.GetExpansionByID(3).enabled = false;         
                    ExpansionManager.GetExpansionByID(4).enabled = false; 
                    continue;     

                }
                case 7 -> {
                    // Continue the game
                    gameState = GameState.PLAYING;

                }
                default -> {
                    System.out.println("Please specify a valid number");
                }
            }

            scanner.close();
        }
    }     
}

