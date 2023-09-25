import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum GameState { START, SETUP, PLAYING, ENDED}

public class GameManager {

    public GameState gameState = GameState.START;

    public List<Player> players = new ArrayList<>();  
    public List<Dice> dices = new ArrayList<>();
    public int turnIndex = 0;

    private final int scoreRequirement = 40;

    public void Start(){

        // Create a welcome message using the StringBuilder
        StringBuilder builder = new StringBuilder();
 
        builder.append(new String("Welcome to the dice game! \n\n"));

        builder.append(new String("(1) Play. \n"));
        builder.append(new String("(2) Rules. \n"));

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
                    DisplayRules();
                }
                default -> {
                    System.out.println("Please specify a valid number");
                }
            }
        }

        scanner.close();
    }

    private void DisplayRules(){
        // Create a message to display the rules using the StringBuilder
        StringBuilder builder = new StringBuilder();
 
        builder.append(new String("The rules are simple! \n\n"));

        builder.append(new String("1. It requires two players to play the dice game.\n"));
        builder.append(new String("2. You will take turns shaking the cup and rolling two dice. \n"));
        builder.append(new String("3. The sum of the two dices will be added to your score. \n"));
        builder.append(new String("4. The first player to reach 40 points has won the game. \n"));

        // Print the message
        System.out.println(builder.toString());
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
            // Display a message.
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
    

    public void OnPlayState(){
                    
        // Create a scanner to read the next input
        Scanner scanner = new Scanner(System.in);

        // Loop through aslong as no player has won the game
        while(gameState == GameState.PLAYING){

            // Get the player from the 'Turn Index'
            Player playerTurn = players.get(turnIndex);   

            // Print a message
            System.out.println("\nIt is " + playerTurn.name + " turn. \nPress any key to roll the dices." );

            // Await any key input.
            scanner.nextLine();

            // Roll the dice
            OnDiceRoll(playerTurn);

            // Increase the 'Turn Index'
            SetNextPlayerTurn();
        }
    }

    private void OnDiceRoll(Player player){
        // Get the sum of the values from the dices
        int diceSum = 0;
        for (Dice dice : dices) {
            int value = dice.rollDice();
            diceSum += value;
        }

        // Add the sum to the player score
        player.score += diceSum;

        // Check if any of the expansion rules apply to the dice throw.
        if(!CheckExpansionRules(player)){

            // Othwerise, handle the game loop based on the current players score.
            if(player.score >= scoreRequirement){
                System.out.println(player.name + " has rolled [" + diceSum + "] and has won with a score of " + player.score);
                gameState = GameState.ENDED;
            }
            else{
                System.out.println(player.name + " has rolled [" + diceSum + "] and has a score of " + player.score);
                
                // Set the previous roll value
                player.lastRoll = diceSum;
            }
        }
    }

    private boolean CheckExpansionRules(Player player){
        for (Expansion expansion : App.expansionManager.expansions) {
            // Check if this expansion is enabled, otherwise return
            if(!expansion.enabled) return false;

            // Check Expansion 1
            if(expansion.id == 1){
                if(expansion.CheckRules(dices)){
                    System.out.println("Ouch! " + player.name + " rolled two [1] and their score has been reset to 0!");
                    player.score = 0;

                    return true;
                }
            }
            // Check Expansion 2
            if(expansion.id == 2){
                if(expansion.CheckRules(dices)){
                    // Insert rule actions here

                    return true;
                }
            }
            // Check Expansion 3
            if(expansion.id == 3){
                if(expansion.CheckRules(dices)){
                    System.out.println("Nice! " + player.name + " rolled two [6] twice in a row and has won the game! ");
                    gameState = GameState.ENDED;
                    
                    return true;
                }
            }
            // Check Expansion 1
            if(expansion.id == 4){
                if(expansion.CheckRules(dices)){
                    // Insert rule actions here
                    
                    return true;
                }
            }
        }

        return false;
    }

    private void SetNextPlayerTurn(){
        turnIndex += 1;

        if(turnIndex > players.size() - 1){
            turnIndex = 0;
        }
    }

    public void OnEndState(){
        // Create a end message using the StringBuilder
        StringBuilder builder = new StringBuilder();

        builder.append(new String("\nThanks for playing!\n"));

        // Print the message
        System.out.println(builder.toString());      
    }

}

