import java.util.List;

public class Expansion {
    public int id;
    public boolean enabled = false;

    public Expansion (int id, boolean enabled){
        this.id = id;
        this.enabled = false;
    } 

    public String Description(){
        return "";
    }

    public boolean CheckRules(List<Dice> dices){
        return true;
    }

    public void ApplyRules(Player player, List<Dice> dices){

    }
}

class Exp1 extends Expansion {

    public Exp1(int id, boolean enabled) {
        super(id, enabled);
    }

    @Override
    public String Description() {
        return "If the player rolls [1] [1], the player loses all their points.\n";

    }

    @Override
    public boolean CheckRules(List<Dice> dices) {

        int value1 = dices.get(0).getRollValue();
        int value2 = dices.get(1).getRollValue();

        if (value1 == value2 && value1 == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void ApplyRules(Player player, List<Dice> dices){
        System.out.println("Ouch! " + player.name + " score has been reset to 0!");
        player.ResetScore();
    }
}

class Exp2 extends Expansion {

    public Exp2(int id, boolean enabled) {
        super(id, enabled);   
    }

    @Override
    public String Description(){
        return "If the player rolls two equals, the player gets an extra turn.\n";
    }

    @Override
    public boolean CheckRules(List<Dice> dices) {
        int value1 = dices.get(0).getRollValue();
        int value2 = dices.get(1).getRollValue();

        if (value1 == value2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void ApplyRules(Player player, List<Dice> dices){
        // Print a message
        System.out.println(player.name + " has an extra turn");
    }
}

class Exp3 extends Expansion {

    public Exp3(int id, boolean enabled) {
        super(id, enabled);   
    }

    @Override
    public String Description(){
        return "If the player in their previous turn, rolled [6] [6], and rolls the same in their next roll, they win the game.\n";
    }

    @Override
    public boolean CheckRules(List<Dice> dices) {

        // Get the player from the 'Turn Index'
        Player playerTurn = App.gameManager.players.get(App.gameManager.turnIndex); 

        //Get the sum of the last roll
        int lastRoll = playerTurn.lastRoll;

        // Get the sum of the roll values of the dices
        int sum = dices.get(0).getRollValue() + dices.get(1).getRollValue();
        
        // Determine if the player is the winner
        var winner = sum == 12 && sum == lastRoll ? true : false;
        return winner;
    }
    
    @Override
    public void ApplyRules(Player player, List<Dice> dices){
        System.out.println("Nice! " + player.name + " rolled [6] [6] twice in a row and has won the game!");
        App.gameManager.gameState = GameState.ENDED;
    }
}

class Exp4 extends Expansion {

    public Exp4(int id, boolean enabled) {
        super(id, enabled);   
    }

    @Override
    public String Description(){
        return "After reaching 40 points, the player has to roll two equals to win the game.\n";
    }

    @Override
    public boolean CheckRules(List<Dice> dices) {
        // Get the player from the 'Turn Index'
        Player player = App.gameManager.players.get(App.gameManager.turnIndex); 

        // Rule applies if the players score has reached 40 or above.
        if((player.score) >= 40){
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public void ApplyRules(Player player, List<Dice> dices){
        // Get the rolls of the dices
        int roll1 = dices.get(0).getRollValue();
        int roll2 = dices.get(1).getRollValue();
        
        if(roll1 == roll2){
            if(player.score >= 40){
                System.out.println(player.name + " has won the game!");
                App.gameManager.gameState = GameState.ENDED;
            }
        }
    }
}
