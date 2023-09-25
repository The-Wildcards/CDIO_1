import java.util.Hashtable;

public class DiceTest {
    public static void main(String args[]) {
        // Hashtable for storage of roll counts of the individual dice
        Hashtable<Integer,Integer> diceStats = new Hashtable<Integer,Integer>() {{
            for (int i = 1; i <= 6; i++) {
                put(i, 0);
            }
        }};

        // Dice object for rolling
        Dice dice = new Dice(0);

        // Variable for storage of roll in following for loop
        int roll;

        // Rolling the dice 1000 times
        for (int i = 0; i < 1000; i++) {
            roll = dice.rollDice(); // Store dice roll in a variable
            diceStats.replace(roll, diceStats.get(roll) + 1); // Add 1 to the count of the number that was rolled
        }
    }
}
