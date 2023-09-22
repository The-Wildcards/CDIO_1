import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameManager {
    
    public static List<Player> players = new ArrayList<>();  
    //public static List<Dice> dices = new ArrayList<>();

    public static void Initialize(int players){
        // Create the amount of players for the game
        CreatePlayers(players);

        // Setup the dice
        // CreateDice(2)
    }

    private static void CreatePlayers(int amount){
        // Create a scanner to read the next input
        Scanner input = new Scanner(System.in);

        for(int i = 0; i < amount; i++){
            // Display a command message.
            System.out.println("Insert a name for Player " + (i + 1));

            // Set the name.
            String name = input.nextLine();

            // Create a new player with the specified name.
            Player player = new Player(name, 0);

            // Add the player to the list of players.
            if(!players.contains(player)){
                players.add(player);
            }
        }

        // Close the scanner as to ensure no resource leaks.
        input.close();
    }

    private static void CreateDice(int amount){
        for(int i = 0; i < amount; i++){
            //Dice dice = new Dice();

            //if(!dices.contains(player)){
            //    dices.add(player);
            //}
        }
    }
}
