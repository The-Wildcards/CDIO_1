import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpansionManager {

    public List<Expansion> exspansions = new ArrayList<>();

    public void GenerateExpansions(){
        Expansion exp1 = new Exp1(1, false);
        Expansion exp2 = new Exp2(2, false);
        Expansion exp3 = new Exp3(3, false);
        Expansion exp4 = new Exp4(4, false);

        exspansions.add(exp1);;
        exspansions.add(exp2);;
        exspansions.add(exp3);;
        exspansions.add(exp4);;
    }

    private Expansion GetExpansionByID(int id) {
        Expansion exp = exspansions.stream().filter(x -> x.id == id).findFirst().orElse(null);
        return exp;
    }

    private void SetExpansionActive(int id){
        Expansion exp = GetExpansionByID(id);
        exp.enabled = !exp.enabled;
    }

    private void SetAllExpansionsActive(boolean enabled){
        for (Expansion expansion : exspansions) {
            expansion.enabled = enabled;
        }
    }

    public void SetupExpansions() {
        // Loop through until the gamestate has been changed
        while(App.gameManager.gameState == GameState.SETUP){

            // Create a message explaining the expansions
            StringBuilder builder = new StringBuilder();
                
            builder.append(new String("Would you like to include any expansions? \n\n"));

            for(int i = 0; i < exspansions.size(); i++){

                // Get the rules of the expansion as string
                String ruleStr = exspansions.get(i).Description();

                // Set the ID
                int id = i + 1;

                // Get a color based on whether or not the expansion is activated
                String color = exspansions.get(i).enabled ? ColorUtils.TEXT_GREEN : ColorUtils.TEXT_RED;

                builder.append(new String(color + "(" + (id) + ")" + " Expansion " + (id) + ": " + ColorUtils.TEXT_WHITE + ruleStr ));
            }

            // Add expansion options to the message
            builder.append(new String("(5) All of the above \n"));
            builder.append(new String("(6) None \n\n"));

            builder.append(new String("(7) Continue"));
            
            // Display the message
            System.out.println(builder.toString());

            // Create a scanner to read the next input
            Scanner scanner = new Scanner(System.in);

            int input = scanner.nextInt();

            switch (input) {
                case 1 -> {
                    // Enable expansion 1
                    SetExpansionActive(1);
                    continue;
                }
                case 2 -> {
                    // Enable expansion 2
                    SetExpansionActive(2);
                    continue; 
                }
                case 3 -> {
                    // Enable expansion 3
                    SetExpansionActive(3);
                    continue;
                }
                case 4 -> {
                    // Enable expansion 4
                    SetExpansionActive(4);
                    continue;  
                }
                case 5 -> {
                    // Enable all expansions
                    SetAllExpansionsActive(true);
                    continue;
                }
                case 6 -> {
                    // Disable all expansions
                    SetAllExpansionsActive(false);
                    continue;

                }
                case 7 -> {
                    // Continue the game
                    App.gameManager.gameState = GameState.PLAYING;
                }
                default -> {
                    System.out.println("Please specify a valid number");
                }
            }

            // Close the scanner as to ensure no resource leaks.
            scanner.close();
        }
    }
}
