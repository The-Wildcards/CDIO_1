public class Expansion {
    public int id;
    public boolean enabled = false;

    public Expansion (int id, boolean enabled){
        this.id = id;
        this.enabled = false;
    } 

    public String GetRulesAsString(){
        return "";
    }

    public boolean CheckRules(){
        return true;
    }
}

class Exp1 extends Expansion {

    public Exp1(int id, boolean enabled) {
        super(id, enabled);
    }

    @Override
    public String GetRulesAsString() {
        return "If the player rolls two 1, the player loses all their points.\n";

    }

    @Override
    public boolean CheckRules() {

        // Get the roll values of the dices
        int dice1 = GameManager.dices.get(0).getRollValue();
        int dice2 = GameManager.dices.get(1).getRollValue();

        if (dice1 == dice2) {
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
    public String GetRulesAsString(){
        return "If the player rolls two equals, the player gets an extra turn.\n";
    }

    @Override
    public boolean CheckRules() {
        return false;
    }
}

class Exp3 extends Expansion {

    public Exp3(int id, boolean enabled) {
        super(id, enabled);   
    }

    @Override
    public String GetRulesAsString(){
        return "If the player in his previous throw, rolled two 6, and rolls two 6 in this roll, he wins the game.\n";
    }

    @Override
    public boolean CheckRules() {

        //Get the sum of the last roll
        int lastRoll = GameManager.lastRoll;

        // Get the roll values of the dices
        int dice1 = GameManager.dices.get(0).getRollValue();
        int dice2 = GameManager.dices.get(1).getRollValue();
        int sum = dice1 + dice2;

        var winner = sum == 12 && sum == lastRoll ? true : false;
        return winner;
    }
}


class Exp4 extends Expansion {

    public Exp4(int id, boolean enabled) {
        super(id, enabled);   
    }

    @Override
    public String GetRulesAsString(){
        return "After reaching 40 points, the player has to roll two equals to win the game.\n";
    }

    @Override
    public boolean CheckRules() {
        return false;
    }
}
