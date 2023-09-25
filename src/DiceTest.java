public class DiceTest {
    public static void main(String args[]) {
        int[] results = new int[1000];

        Dice dice = new Dice(0);

        for (int i = 0; i < 1000; i++) {
            results[i] = dice.rollDice();
        }
    }
}
