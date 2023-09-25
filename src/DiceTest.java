import java.util.Hashtable;

public class DiceTest {
    public static void main(String args[]) {
        System.out.println("Initializing dice test..."); // Announces dice test

        // Hashtable for storage of roll counts of the individual dice
        var diceStats = new Hashtable<Integer,Integer>() {{
            for (int i = 1; i <= 6; i++) { // Iterating through the six possibilities for dice roll results
                put(i, 0); // Sets the initial value of all counts to 0
            }
        }};

        // Hashtable of statistically expected values for comparison with diceStats
        var expectedStats = new Hashtable<Integer,Double>() {{
            for (int i = 1; i <= 6; i++) { // Iterating through the six possibilities for dice roll results
                put(i, 1000d/6d); // Sets the initial value of all counts to 1000/6
            }
        }};

        // Initializes hashtable of percentage differences between results and statistically expected results
        var percentages = new Hashtable<Integer,Double>();

        // Dice object for rolling
        Dice dice = new Dice(0);

        // Variable for storage of roll in following for loop
        int roll;

        System.out.println("Rolling dice..."); // Announcing rolling phase

        // Rolling the dice 1000 times
        for (int i = 0; i < 1000; i++) {
            roll = dice.rollDice(); // Store dice roll in a variable

            diceStats.replace(roll, diceStats.get(roll) + 1); // Add 1 to the count of the number that was rolled

            System.out.println("Rolled a " + roll); // Announces roll
        }

        // Calculate percentage differences
        percentages = compareStats(diceStats, expectedStats);

        // Display results
        displayResults(diceStats, percentages);
    }

    // Gives the percentage difference between a hashtable of roll stats and the expected stats
    static Hashtable<Integer,Double> compareStats(Hashtable<Integer,Integer> rollsTable, Hashtable<Integer,Double> expectedTable) {
        // Initializes the hashtable of percentage differences
        var percentages = new Hashtable<Integer,Double>();

        // Initializes variables for the values of the hashtables
        double count, expected, diff;

        // Iterating through the six possibilities for dice roll results
        for (int i = 1; i <= 6; i++) {
            count = (double)(rollsTable.get(i)); // Roll count for the result, typecasted to double for division
            expected = expectedTable.get(i);

            // Calculates the percentage difference
            diff = (Math.abs(count - expected)) / ((count + expected) / 2) * 100;

            // Add the results to the hashtable
            percentages.put(i, diff);
        }

        // Return full hashtable
        return percentages;
    }

    static void displayResults(Hashtable<Integer,Integer> rollsTable, Hashtable<Integer,Double> percentagesTable) {
        // Variable for whether the test has succeeded
        boolean testSucceeded = true;
        
        // Printing
        System.out.println();
        System.out.println("Test results:");

        // Iterating through the tables
        for (int i = 1; i <= 6; i++) {
            // Set testSucceeded to false if percentage > 1
            if (percentagesTable.get(i) > 1) { testSucceeded = false; }

            // Print statistics
            System.out.println(
                String.format("%d's rolled: %d (%f%% off expected result)",
                i, rollsTable.get(i), percentagesTable.get(i)
            ));
        }

        // Announces test verdict
        System.out.println();
        System.out.println("Test " +(
            testSucceeded ? "succeeded" : "failed"
        ));
    }
}