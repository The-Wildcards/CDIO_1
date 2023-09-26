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
        builder.append(new String("2. You will take turns rolling two dice. \n"));
        builder.append(new String("3. The sum of the two dice will be added to your score. \n"));
        builder.append(new String("4. The first player to reach 40 points has won the game. \n\n"));
        
        builder.append(new String("(1) Play. \n"));

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
            System.out.println("\nIt is " + playerTurn.name + " turn. \nPress ENTER to roll the dice." );

            // Await any key input.
            scanner.nextLine();

            // Roll the dice
            OnDiceRoll(playerTurn);
        }
    }

    private void OnDiceRoll(Player player){
        // Roll each of the dice
        int roll1 = dices.get(0).rollDice();
        int roll2 = dices.get(1).rollDice();

        // Get the sum of the values from the dices
        int diceSum = roll1 + roll2;

        // Create a display score to display in the message
        int displayScore = player.score >= scoreRequirement ? player.score : player.score + (roll1 + roll2);

        
        // Display the dice rolls and score
        System.out.println(player.name + " has rolled [" + roll1 + "] " + "["+ roll2 + "]" + " and has a score of " + displayScore);
        
        // If Exp(4) is enabled and the score is above 40, then print out the exp message.
        if(displayScore >= 40 && App.expansionManager.expansions.get(3).enabled){
            System.out.println(player.name + " has to roll two equals to win the game");
        }

        // Check if any of the expansion rules apply to the dice throw.
        List<Expansion> expansionRules = GetExpansionRules();

        // Check if there are any expansions rules to apply
        if(expansionRules.size() > 0){
            // Apply each of the expansion rules
            for (Expansion expansion : expansionRules) {
                expansion.ApplyRules(player, dices);
            }

            // If Exp(2) is not enabled, then increase the turn index.
            Expansion exp2 = expansionRules.stream().filter(x -> x.id == 2).findFirst().orElse(null);
            if(exp2 == null){
                // Increase the 'Turn Index'
                SetNextPlayerTurn();
            }

            // Check if the player has won the game
            if(player.score >= scoreRequirement){
                // If Exp(4) is not enabled, then end the game
                Expansion exp4 = expansionRules.stream().filter(x -> x.id == 4).findFirst().orElse(null);
                if(exp4 == null){
                    System.out.println(player.name + " has won with a score of " + player.score);
                    gameState = GameState.ENDED;            
                }
            }           
        }
        else{
            // Increase the 'Turn Index'
            SetNextPlayerTurn();

            // Check if the player has won the game
            if(player.score >= scoreRequirement){
                System.out.println(player.name + " has won with a score of " + player.score);
                gameState = GameState.ENDED;
            }
        }

        // Set the last roll of the dice
        player.lastRoll = diceSum; 

        // Check if whether or not exp(1) is included
        Expansion exp1 = expansionRules.stream().filter(x -> x.id == 1).findFirst().orElse(null);
        if(exp1 == null && diceSum != 2){
            // Add the sum to the player score     
            player.AddScore(diceSum);
        }
    }

    private List<Expansion> GetExpansionRules(){
        List<Expansion> expansionRules = new ArrayList<>();

        for (Expansion expansion : App.expansionManager.expansions) {
            // Check if this expansion is enabled, otherwise return1
            if(!expansion.enabled) { continue; }

            // Loop trough each of the expansions and check if their rules are met.
            switch (expansion.id) {
                case 1 -> {
                    if(expansion.CheckRules(dices)){
                        expansionRules.add(expansion);
                    }
                    break;
                }
                case 2 -> {
                   if(expansion.CheckRules(dices)){
                        expansionRules.add(expansion);
                    }
                    break;
                }
                case 3 -> {
                   if(expansion.CheckRules(dices)){
                        expansionRules.add(expansion);
                    }
                    break;
                }
                case 4 -> {
                   if(expansion.CheckRules(dices)){
                        expansionRules.add(expansion);
                    }
                    break;  
                }
                default -> {
                    System.out.println("Cannot find expansion...");
                }
            }
        }

        return expansionRules;
    }

    private void SetNextPlayerTurn(){
        // Increase the 'turn index'
        turnIndex += 1;

        // Limit the turn index to the players size
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

