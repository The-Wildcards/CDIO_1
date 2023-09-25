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
}

class Exp1 extends Expansion {

    public Exp1(int id, boolean enabled) {
        super(id, enabled);
    }

    @Override
    public String Description() {
        return "If the player rolls two 1, the player loses all their points.\n";

    }

    @Override
    public boolean CheckRules(List<Dice> dices) {

        if (dices.get(0).getRollValue() == dices.get(1).getRollValue()) {
            return true;
        } else {
            return false;
        }
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
        return false;
    }
}

class Exp3 extends Expansion {

    public Exp3(int id, boolean enabled) {
        super(id, enabled);   
    }

    @Override
    public String Description(){
        return "If the player in their previous turn, rolled two [6], and rolls two [6] in their next roll, they win the game.\n";
    }

    @Override
    public boolean CheckRules(List<Dice> dices) {

        //Get the sum of the last roll
        int lastRoll = App.gameManager.lastRoll;

        // Get the sum of the roll values of the dices
        int sum = dices.get(0).getRollValue() + dices.get(1).getRollValue();

        var winner = sum == 12 && sum == lastRoll ? true : false;
        return winner;
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
        return false;
    }
}
